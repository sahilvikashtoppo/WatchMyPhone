package com.watchmyphone.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.watchmyphone.data.local.entity.AppUsageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppUsageDao {

    @Insert
    suspend fun insert(usage: AppUsageEntity)

    @Query("SELECT * FROM app_usage WHERE sessionId = :sessionId ORDER BY timestamp DESC")
    fun observeUsage(sessionId: Long): Flow<List<AppUsageEntity>>

    @Query("DELETE FROM app_usage WHERE sessionId = :sessionId")
    suspend fun deleteForSession(sessionId: Long)
}
