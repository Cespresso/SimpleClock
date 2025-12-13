package com.example.apptemplate2025winter.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptemplate2025winter.ui.theme.AppTemplate2025WinterTheme

/**
 * 設定画面
 *
 * @param viewModel 設定画面のViewModel
 * @param onNavigateUp 戻るイベント
 */
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    SettingsScreenContent(onNavigateUp = onNavigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(onNavigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("設定") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "背景色")
            // TODO: 色選択UIを実装
            Text(text = "文字色")
            // TODO: 色選択UIを実装
            Text(text = "時刻文字サイズ")
            // TODO: サイズ選択UIを実装
            Text(text = "日付文字サイズ")
            // TODO: サイズ選択UIを実装
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AppTemplate2025WinterTheme {
        SettingsScreenContent(onNavigateUp = {})
    }
}
