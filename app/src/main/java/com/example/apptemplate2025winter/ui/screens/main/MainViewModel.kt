package com.example.apptemplate2025winter.ui.screens.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptemplate2025winter.data.local.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

/**
 * メイン画面のViewModel
 *
 * 画面の状態管理とビジネスロジックの呼び出しを担当します。
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.JAPAN)
    private val dateFormat = SimpleDateFormat("yyyy年M月d日", Locale.JAPAN)

    init {
        // 設定の変更を監視
        combine(
            settingsDataStore.backgroundColor,
            settingsDataStore.textColor,
            settingsDataStore.timeSize,
            settingsDataStore.dateSize
        ) { backgroundColor, textColor, timeSize, dateSize ->
            _uiState.update {
                it.copy(
                    backgroundColor = Color(backgroundColor.drop(2).toLong(16)),
                    textColor = Color(textColor.drop(2).toLong(16)),
                    timeSize = timeSize,
                    dateSize = dateSize
                )
            }
        }.launchIn(viewModelScope)

        // 時刻の更新
        viewModelScope.launch {
            while (true) {
                val currentTime = Date()
                _uiState.update {
                    it.copy(
                        time = timeFormat.format(currentTime),
                        dayOfWeek = dayOfWeekFormat.format(currentTime),
                        date = dateFormat.format(currentTime)
                    )
                }
                delay(1000)
            }
        }
    }

    // 画面のイベントハンドラをここに追加します
    // 例:
    // fun onButtonClick() {
    //     viewModelScope.launch {
    //         _uiState.update { it.copy(isLoading = true) }
    //         // 処理を実行
    //         _uiState.update { it.copy(isLoading = false) }
    //     }
    // }
}
