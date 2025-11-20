package com.watchmyphone.data.repository

import com.watchmyphone.data.local.IntruderDao
import com.watchmyphone.data.local.IntruderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntruderRepository @Inject constructor(private val dao: IntruderDao) {

    fun observeIntruders(): Flow<List<IntruderEntity>> = dao.observeAll()

    suspend fun saveIntruder(imagePath: String?, event: String) {
        dao.insert(IntruderEntity(timestamp = System.currentTimeMillis(), imagePath = imagePath, event = event))
    }

    suspend fun deleteIntruder(id: Long) = dao.deleteById(id)
}
