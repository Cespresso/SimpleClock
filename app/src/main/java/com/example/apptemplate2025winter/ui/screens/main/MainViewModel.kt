package com.example.apptemplate2025winter.ui.screens.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * メイン画面のViewModel
 *
 * 画面の状態管理とビジネスロジックの呼び出しを担当します。
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    // UseCaseやRepositoryをここに注入します
    // 例: private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

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
