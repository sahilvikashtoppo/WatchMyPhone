package com.watchmyphone.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IntruderDao {

    @Insert
    suspend fun insert(entity: IntruderEntity): Long

    @Query("SELECT * FROM intruders ORDER BY timestamp DESC")
    fun observeAll(): Flow<List<IntruderEntity>>

    @Query("DELETE FROM intruders WHERE id = :id")
    suspend fun deleteById(id: Long)
}
