package com.watchmyphone.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat

class ScreenReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, MonitorService::class.java)
        when (intent.action) {
            Intent.ACTION_SCREEN_ON -> {
                Log.d("ScreenReceiver", "ACTION_SCREEN_ON")
                serviceIntent.action = MonitorService.ACTION_CAPTURE
                ContextCompat.startForegroundService(context, serviceIntent)
            }
            Intent.ACTION_USER_PRESENT -> {
                Log.d("ScreenReceiver", "ACTION_USER_PRESENT")
                serviceIntent.action = MonitorService.ACTION_USER_PRESENT
                ContextCompat.startForegroundService(context, serviceIntent)
            }
        }
    }
}
