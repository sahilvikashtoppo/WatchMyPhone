package com.watchmyphone.data.repository

import androidx.room.Insert
import com.watchmyphone.data.local.dao.IntruderDao
import com.watchmyphone.data.local.entity.IntruderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntruderRepository @Inject constructor(private val dao: IntruderDao) {

    fun observeIntruders(): Flow<List<IntruderEntity>> = dao.observeAll()


    suspend fun saveIntruder(imagePath: String?, event: String): Long {
        val entity = IntruderEntity(timestamp = System.currentTimeMillis(), imagePath = imagePath, event = event)
        return dao.insert(entity)
    }

    suspend fun deleteIntruder(id: Long) = dao.deleteById(id)
}
