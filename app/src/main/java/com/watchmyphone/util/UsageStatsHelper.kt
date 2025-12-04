package com.watchmyphone.util

import android.app.usage.UsageStatsManager
import android.content.Context
import android.app.usage.UsageEvents
import android.util.Log
import javax.inject.Inject

class UsageStatsHelper @Inject constructor(private val ctx: Context) {

    fun getForegroundAppPackageName(): String? {
        val usageStatsManager = ctx.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        val currentTime = System.currentTimeMillis()
        val startTime = currentTime - 5000 // last 5 seconds

        val usageEvents = usageStatsManager.queryEvents(startTime, currentTime)
        var lastPackage: String? = null

        val event = UsageEvents.Event()

        while (usageEvents.hasNextEvent()) {
            usageEvents.getNextEvent(event)
            if (event.eventType == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                Log.d("UsageStatsHelper", "lastPackage , ${event.packageName}")
                lastPackage = event.packageName
            }
        }

        return lastPackage
    }
}

