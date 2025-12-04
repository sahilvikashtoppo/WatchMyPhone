package com.watchmyphone.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.watchmyphone.R
import com.watchmyphone.data.repository.AppUsageRepository
import com.watchmyphone.data.repository.IntruderRepository
import com.watchmyphone.util.Camera2Helper
import com.watchmyphone.util.UsageStatsHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class MonitorService : LifecycleService() {

    companion object {
        const val CHANNEL_ID = "monitor_service"
        const val NOTIFICATION_ID = 101

        const val ACTION_START_MONITORING = "com.watchmyphone.START_MONITORING"
        const val ACTION_STOP_MONITORING = "com.watchmyphone.STOP_MONITORING"
        const val ACTION_CAPTURE = "com.watchmyphone.ACTION_CAPTURE"
        const val ACTION_USER_PRESENT = "com.watchmyphone.ACTION_USER_PRESENT"
        const val ACTION_SCREEN_OFF = "com.watchmyphone.ACTION_SCREEN_OFF"

        @Volatile
        var isRunning = false
            private set
    }

    @Inject lateinit var cameraHelper: Camera2Helper
    @Inject lateinit var repo: IntruderRepository
    @Inject lateinit var usageStatsHelper: UsageStatsHelper
    @Inject lateinit var usageRepo: AppUsageRepository



    private var screenReceiver: BroadcastReceiver? = null
    private var monitoringJob: Job? = null

    private var sessionId: Long? = null
    private var lastPackage: String? = null

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        startForegroundSafely()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d("MonitorService", "onStartCommand , Intent : ${intent?.action}")
        when (intent?.action) {
            ACTION_START_MONITORING -> startMonitoring()
            ACTION_STOP_MONITORING -> stopMonitoring()
            ACTION_CAPTURE -> lifecycleScope.launch(Dispatchers.IO) { captureImage("screen_on") }
            ACTION_USER_PRESENT -> lifecycleScope.launch(Dispatchers.IO) {
                val id = captureImage("user_unlocked")
                if (id != null) {
                    startSession(id)
                }
            }
            ACTION_SCREEN_OFF -> endSession()
        }
        return START_STICKY
    }

    private fun startSession(id: Long) {
        Log.d("MonitorService", "startSession , sessionId : $id")
        sessionId = id
        monitorForegroundApps()
    }

    private fun endSession() {
        sessionId = null
        monitoringJob?.cancel()
    }

    private fun monitorForegroundApps() {
        Log.d("MonitorService", "monitorForegroundApps")
        monitoringJob?.cancel()
        monitoringJob = lifecycleScope.launch(Dispatchers.IO) {
            while (sessionId != null) {
                val packageName = getForegroundApp()
                if (packageName != lastPackage) {
                    lastPackage = packageName
                    sessionId?.let {
                        if (packageName != null) {
                            usageRepo.saveUsage(it, packageName)
                        }
                    }
                }
                delay(2000)
            }
        }
    }

    private fun getForegroundApp(): String? {
        Log.d("MonitorService", "getForegroundApp")
        return usageStatsHelper.getForegroundAppPackageName()
    }



    private fun startMonitoring() {
        if (screenReceiver != null) return

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_USER_PRESENT)
        }

        screenReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.action) {
                    /*Intent.ACTION_SCREEN_ON -> triggerCapture(ACTION_CAPTURE)*/
                    Intent.ACTION_USER_PRESENT -> triggerCapture(ACTION_USER_PRESENT)
                    Intent.ACTION_SCREEN_OFF -> triggerCapture(ACTION_SCREEN_OFF)
                }
            }
        }

        registerReceiver(screenReceiver, filter)
    }

    private fun stopMonitoring() {
        unregisterScreenReceiver()
        monitoringJob?.cancel()
        monitoringJob = null
        isRunning = false
        stopForeground(true)
        stopSelf()
    }

    private fun unregisterScreenReceiver() {
        try {
            screenReceiver?.let {
                unregisterReceiver(it)
                screenReceiver = null
            }
        } catch (_: Exception) { }
    }

    private fun triggerCapture(action: String) {
        val serviceIntent = Intent(this, MonitorService::class.java).apply { this.action = action }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(serviceIntent)
        else
            startService(serviceIntent)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private suspend fun captureImage(reason: String): Long? {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) return null

        return try {
            val dir = File(getExternalFilesDir(null), "intruders").apply { if (!exists()) mkdirs() }
            val fileName = "intruder_${System.currentTimeMillis()}.jpg"
            val path = cameraHelper.captureFrontImage(dir, fileName)
            repo.saveIntruder(path, reason) // <-- returns inserted ID
        } catch (e: Exception) {
            e.printStackTrace()
            repo.saveIntruder(null, "capture_failed")
        }
    }

    private fun startForegroundSafely() {
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "WatchMyPhone Monitoring",
                NotificationManager.IMPORTANCE_LOW
            )
            nm.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("WMP")
            .setContentText("Protection enabled")
            .setSmallIcon(R.drawable.protection)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterScreenReceiver()
        monitoringJob?.cancel()
        lifecycleScope.cancel()
        isRunning = false
    }

    override fun onBind(intent: Intent): IBinder? = super.onBind(intent)
}
