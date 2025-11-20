package com.watchmyphone.util

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import javax.inject.Inject

class UsageStatsHelper @Inject constructor(private val ctx: Context) {

    fun queryUsage(from: Long, to: Long): List<UsageStats> {
        val usm = ctx.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        return usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, from, to) ?: emptyList()
    }
}
