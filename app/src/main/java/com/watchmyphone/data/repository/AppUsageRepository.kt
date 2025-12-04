package com.watchmyphone.data.repository

import com.watchmyphone.data.local.dao.AppUsageDao
import com.watchmyphone.data.local.entity.AppUsageEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppUsageRepository @Inject constructor(private val dao: AppUsageDao) {
    fun observeUsage(sessionId: Long) = dao.observeUsage(sessionId)

    suspend fun saveUsage(sessionId: Long, packageName: String) {
        dao.insert(AppUsageEntity(sessionId = sessionId, packageName = packageName, timestamp = System.currentTimeMillis()))
    }
}
