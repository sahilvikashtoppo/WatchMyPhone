package com.watchmyphone.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootReceiver", "onReceive")
            /*val i = Intent(context, MonitorService::class.java)
            i.action = MonitorService.ACTION_CAPTURE
            ContextCompat.startForegroundService(context, i)*/
        }
    }
}
