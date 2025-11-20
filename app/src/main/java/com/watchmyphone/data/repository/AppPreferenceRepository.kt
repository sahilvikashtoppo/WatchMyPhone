package com.watchmyphone.data.repository

import android.content.Context
import com.watchmyphone.data.local.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppPreferenceRepository @Inject constructor(context: Context) {

    private val preferences = AppPreferences(context)

    val serviceEnabled: Flow<Boolean> get() = preferences.serviceEnabled

    suspend fun setServiceEnabled(enabled: Boolean) {
        preferences.setServiceEnabled(enabled)
    }
}