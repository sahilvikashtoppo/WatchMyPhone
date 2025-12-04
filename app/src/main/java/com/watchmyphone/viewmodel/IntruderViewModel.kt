package com.watchmyphone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchmyphone.data.local.entity.IntruderEntity
import com.watchmyphone.data.repository.AppPreferenceRepository
import com.watchmyphone.data.repository.AppUsageRepository
import com.watchmyphone.data.repository.IntruderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntruderViewModel @Inject constructor(
    private val repo: IntruderRepository,
    private val appUsageRepo: AppUsageRepository,
    private val appPreferenceRepo: AppPreferenceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<IntruderEntity>>(emptyList())
    val uiState: StateFlow<List<IntruderEntity>> = _uiState.asStateFlow()

    // Observe Service On/Off as a StateFlow
    val serviceEnabled: StateFlow<Boolean> = appPreferenceRepo.serviceEnabled
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    init {
        viewModelScope.launch {
            repo.observeIntruders().collectLatest { list ->
                _uiState.value = list
            }
        }
    }

    fun observeUsage(sessionId: Long) = appUsageRepo.observeUsage(sessionId)

    fun delete(id: Long) = viewModelScope.launch { repo.deleteIntruder(id) }

    fun toggleService(isEnabled: Boolean) = viewModelScope.launch {
        appPreferenceRepo.setServiceEnabled(isEnabled)
    }
}
