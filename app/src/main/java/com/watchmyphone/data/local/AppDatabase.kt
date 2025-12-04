package com.watchmyphone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.watchmyphone.data.local.dao.AppUsageDao
import com.watchmyphone.data.local.dao.IntruderDao
import com.watchmyphone.data.local.entity.AppUsageEntity
import com.watchmyphone.data.local.entity.IntruderEntity

@Database(entities = [IntruderEntity::class, AppUsageEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun intruderDao(): IntruderDao
    abstract fun appUsageDao(): AppUsageDao
}
