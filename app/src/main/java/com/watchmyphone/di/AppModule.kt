package com.watchmyphone.di

import android.content.Context
import androidx.room.Room
import com.watchmyphone.data.local.AppDatabase
import com.watchmyphone.util.Camera2Helper
import com.watchmyphone.util.UsageStatsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(ctx, AppDatabase::class.java, "watch_db").build()
    }

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideIntruderDao(db: AppDatabase) = db.intruderDao()

    @Provides
    @Singleton
    fun provideCameraHelper(@ApplicationContext ctx: Context) = Camera2Helper(ctx)

    @Provides
    @Singleton
    fun provideUsageStatsHelper(@ApplicationContext ctx: Context) = UsageStatsHelper(ctx)

}
