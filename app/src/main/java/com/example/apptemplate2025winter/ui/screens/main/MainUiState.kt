package com.example.apptemplate2025winter.ui.screens.main

import androidx.compose.ui.graphics.Color

data class MainUiState(
    val time: String = "",
    val dayOfWeek: String = "",
    val date: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val backgroundColor: Color = Color(0xFF1B3224),
    val textColor: Color = Color(0xFFFFFFFF),
    val timeSize: Float = 64f,
    val dateSize: Float = 18f
)
