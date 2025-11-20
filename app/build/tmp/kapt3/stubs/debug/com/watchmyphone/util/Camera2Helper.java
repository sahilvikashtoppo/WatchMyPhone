package com.watchmyphone.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J6\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010\u0014J \u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0087@\u00a2\u0006\u0002\u0010\u0019J4\u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006H\u0083@\u00a2\u0006\u0002\u0010\u001eJ\n\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u001e\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@\u00a2\u0006\u0002\u0010$J(\u0010%\u001a\u00020&2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\'\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/watchmyphone/util/Camera2Helper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "capture", "", "session", "Landroid/hardware/camera2/CameraCaptureSession;", "camera", "Landroid/hardware/camera2/CameraDevice;", "reader", "Landroid/media/ImageReader;", "handler", "Landroid/os/Handler;", "cameraId", "(Landroid/hardware/camera2/CameraCaptureSession;Landroid/hardware/camera2/CameraDevice;Landroid/media/ImageReader;Landroid/os/Handler;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "captureFrontImage", "saveDir", "Ljava/io/File;", "fileName", "(Ljava/io/File;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSessionWithParameters", "surfaces", "", "Landroid/view/Surface;", "(Landroid/hardware/camera2/CameraDevice;Ljava/util/List;Landroid/os/Handler;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findFrontCameraId", "getOrientation", "", "openCamera", "id", "(Ljava/lang/String;Landroid/os/Handler;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startPreview", "", "previewSurface", "app_debug"})
public final class Camera2Helper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "Camera2Helper";
    @org.jetbrains.annotations.NotNull()
    private final android.hardware.camera2.CameraManager cameraManager = null;
    
    @javax.inject.Inject()
    public Camera2Helper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.P)
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object captureFrontImage(@org.jetbrains.annotations.NotNull()
    java.io.File saveDir, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.Object openCamera(java.lang.String id, android.os.Handler handler, kotlin.coroutines.Continuation<? super android.hardware.camera2.CameraDevice> $completion) {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.P)
    private final java.lang.Object createSessionWithParameters(android.hardware.camera2.CameraDevice camera, java.util.List<? extends android.view.Surface> surfaces, android.os.Handler handler, java.lang.String cameraId, kotlin.coroutines.Continuation<? super android.hardware.camera2.CameraCaptureSession> $completion) {
        return null;
    }
    
    /**
     * Starts short preview for AE/AWB warm-up.
     */
    private final void startPreview(android.hardware.camera2.CameraCaptureSession session, android.hardware.camera2.CameraDevice camera, android.view.Surface previewSurface, android.os.Handler handler) {
    }
    
    private final java.lang.Object capture(android.hardware.camera2.CameraCaptureSession session, android.hardware.camera2.CameraDevice camera, android.media.ImageReader reader, android.os.Handler handler, java.lang.String cameraId, kotlin.coroutines.Continuation<? super byte[]> $completion) {
        return null;
    }
    
    private final java.lang.String findFrontCameraId() {
        return null;
    }
    
    private final int getOrientation(java.lang.String cameraId) {
        return 0;
    }
}