package com.example.apptemplate2025winter.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptemplate2025winter.ui.theme.AppTemplate2025WinterTheme

/**
 * メイン画面
 *
 * @param viewModel メイン画面のViewModel
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreenContent(
        uiState = uiState,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(uiState: MainUiState) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }else{
            Text("Hello Android!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AppTemplate2025WinterTheme {
        MainScreenContent(
            uiState = MainUiState()
        )
    }
}
