package com.watchmyphone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchmyphone.data.repository.AppPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appPrefRepo: AppPreferenceRepository
) : ViewModel() {

    val notificationEnabled = appPrefRepo.notificationEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    fun setNotificationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            appPrefRepo.setNotificationEnabled(enabled)
        }
    }
}
