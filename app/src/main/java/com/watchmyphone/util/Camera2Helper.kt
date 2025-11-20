package com.watchmyphone.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.hardware.camera2.params.OutputConfiguration
import android.hardware.camera2.params.SessionConfiguration
import android.media.Image
import android.media.ImageReader
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.Surface
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.delay
import javax.inject.Inject

class Camera2Helper @Inject constructor(private val context: Context) {

    private val TAG = "Camera2Helper"
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager

    @RequiresApi(Build.VERSION_CODES.P)
    suspend fun captureFrontImage(saveDir: File, fileName: String): String? = withContext(Dispatchers.IO) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) return@withContext null

        val cameraId = findFrontCameraId() ?: return@withContext null

        val handlerThread = HandlerThread("CameraThread").apply { start() }
        val handler = Handler(handlerThread.looper)

        // Dummy preview surface (required to start AE)
        val dummyTexture = SurfaceTexture(10).apply { setDefaultBufferSize(640, 480) }
        val previewSurface = Surface(dummyTexture)

        val imageReader = ImageReader.newInstance(640, 480, ImageFormat.JPEG, 2)

        var camera: CameraDevice? = null
        var session: CameraCaptureSession? = null

        try {
            camera = openCamera(cameraId, handler)
            session = createSessionWithParameters(camera, listOf(previewSurface, imageReader.surface), handler, cameraId)

            // Warm-up preview before capture
            startPreview(session, camera, previewSurface, handler)
            delay(700) // let AE/AWB stabilize

            val imageBytes = capture(session, camera, imageReader, handler, cameraId)

            val file = File(saveDir, fileName)
            FileOutputStream(file).use { it.write(imageBytes) }
            Log.d(TAG, "Image saved: ${file.absolutePath}")
            return@withContext file.absolutePath

        } catch (e: Exception) {
            Log.e(TAG, "Capture failed", e)
            return@withContext null
        } finally {
            try { session?.close() } catch (_: Exception) {}
            try { camera?.close() } catch (_: Exception) {}
            imageReader.close()
            previewSurface.release()
            dummyTexture.release()
            handlerThread.quitSafely()
        }
    }

    private suspend fun openCamera(id: String, handler: Handler): CameraDevice =
        suspendCancellableCoroutine { cont ->
            val callback = object : CameraDevice.StateCallback() {
                override fun onOpened(device: CameraDevice) = cont.resume(device)
                override fun onDisconnected(device: CameraDevice) {
                    device.close(); cont.resumeWithException(Exception("Camera disconnected"))
                }
                override fun onError(device: CameraDevice, error: Int) {
                    device.close(); cont.resumeWithException(Exception("Camera error: $error"))
                }
            }

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                cont.resumeWithException(SecurityException("Camera permission not granted"))
                return@suspendCancellableCoroutine
            }

            cameraManager.openCamera(id, callback, handler)
        }

    @RequiresApi(Build.VERSION_CODES.P)
    private suspend fun createSessionWithParameters(
        camera: CameraDevice,
        surfaces: List<Surface>,
        handler: Handler,
        cameraId: String
    ): CameraCaptureSession = suspendCancellableCoroutine { cont ->
        val executor = Executor { command -> handler.post(command) }

        val orientation = getOrientation(cameraId)
        val sessionBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).apply {
            set(CaptureRequest.JPEG_ORIENTATION, orientation)
            set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        }.build()

        val outputConfigs = surfaces.map { OutputConfiguration(it) }
        val sessionConfig = SessionConfiguration(
            SessionConfiguration.SESSION_REGULAR,
            outputConfigs,
            executor,
            object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    Log.d(TAG, "Session configured with JPEG_ORIENTATION=$orientation")
                    cont.resume(session)
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {
                    cont.resumeWithException(Exception("Failed to configure session"))
                }
            }
        )
        sessionConfig.sessionParameters = sessionBuilder
        camera.createCaptureSession(sessionConfig)
    }

    /** Starts short preview for AE/AWB warm-up. */
    private fun startPreview(
        session: CameraCaptureSession,
        camera: CameraDevice,
        previewSurface: Surface,
        handler: Handler
    ) {
        val previewRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).apply {
            addTarget(previewSurface)
            set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
            set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
            set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON)
        }.build()

        session.setRepeatingRequest(previewRequest, null, handler)
    }

    private suspend fun capture(
        session: CameraCaptureSession,
        camera: CameraDevice,
        reader: ImageReader,
        handler: Handler,
        cameraId: String
    ): ByteArray = suspendCancellableCoroutine { cont ->
        val orientation = getOrientation(cameraId)

        val captureRequest = camera.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE).apply {
            addTarget(reader.surface)
            set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
            set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
            set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON)
            set(CaptureRequest.JPEG_QUALITY, 100)
            set(CaptureRequest.JPEG_ORIENTATION, orientation)
        }.build()

        Log.d(TAG, "📸 Capturing with JPEG_ORIENTATION=$orientation")

        reader.setOnImageAvailableListener({ r ->
            val image: Image? = try { r.acquireNextImage() } catch (_: Exception) { r.acquireLatestImage() }
            if (image != null) {
                val buffer = image.planes[0].buffer
                val bytes = ByteArray(buffer.remaining()).apply { buffer.get(this) }
                image.close()
                if (cont.isActive) cont.resume(bytes)
            }
        }, handler)

        session.capture(captureRequest, null, handler)
    }

    private fun findFrontCameraId(): String? =
        cameraManager.cameraIdList.firstOrNull {
            val c = cameraManager.getCameraCharacteristics(it)
            c.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT
        }

    private fun getOrientation(cameraId: String): Int {
        val c = cameraManager.getCameraCharacteristics(cameraId)
        val sensorOrientation = c.get(CameraCharacteristics.SENSOR_ORIENTATION) ?: 0
        val facingFront = c.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val rotation = wm.defaultDisplay.rotation

        val deviceDegrees = when (rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }

        val jpegOrientation = if (facingFront)
            (sensorOrientation - deviceDegrees + 360) % 360
        else
            (sensorOrientation + deviceDegrees) % 360

        Log.d(TAG, "sensor=$sensorOrientation, device=$deviceDegrees, facingFront=$facingFront → jpegOrientation=$jpegOrientation")
        return jpegOrientation
    }
}
