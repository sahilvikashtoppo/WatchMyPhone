package com.watchmyphone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IntruderEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun intruderDao(): IntruderDao
}
