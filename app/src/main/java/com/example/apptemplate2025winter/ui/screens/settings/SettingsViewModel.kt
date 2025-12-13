package com.example.apptemplate2025winter.ui.screens.settings

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptemplate2025winter.data.local.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 設定画面のViewModel
 *
 * 画面の状態管理とビジネスロジックの呼び出しを担当します。
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            combine(
                settingsDataStore.backgroundColor,
                settingsDataStore.textColor,
                settingsDataStore.timeSize,
                settingsDataStore.dateSize
            ) { backgroundColor, textColor, timeSize, dateSize ->
                val selectedBgColor = _uiState.value.backgroundColorOptions.find {
                    "0x${it.color.toArgb().toUInt().toString(16).uppercase()}" == backgroundColor
                } ?: _uiState.value.selectedBackgroundColor

                val selectedTextColor = _uiState.value.textColorOptions.find {
                    "0x${it.color.toArgb().toUInt().toString(16).uppercase()}" == textColor
                } ?: _uiState.value.selectedTextColor


                SettingsUiState(
                    selectedBackgroundColor = selectedBgColor,
                    selectedTextColor = selectedTextColor,
                    timeSize = timeSize,
                    dateSize = dateSize
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun onBackgroundColorSelected(color: ColorOption) {
        _uiState.update { it.copy(selectedBackgroundColor = color) }
    }

    fun onTextColorSelected(color: ColorOption) {
        _uiState.update { it.copy(selectedTextColor = color) }
    }

    fun onTimeSizeChange(size: Float) {
        _uiState.update { it.copy(timeSize = size) }
    }

    fun onDateSizeChange(size: Float) {
        _uiState.update { it.copy(dateSize = size) }
    }

    fun saveSettings() {
        viewModelScope.launch {
            val currentState = _uiState.value
            settingsDataStore.saveSettings(
                backgroundColor = "0x${currentState.selectedBackgroundColor.color.toArgb().toUInt().toString(16).uppercase()}",
                textColor = "0x${currentState.selectedTextColor.color.toArgb().toUInt().toString(16).uppercase()}",
                timeSize = currentState.timeSize,
                dateSize = currentState.dateSize
            )
        }
    }
}
