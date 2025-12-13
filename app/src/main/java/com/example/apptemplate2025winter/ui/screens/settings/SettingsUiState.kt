package com.example.apptemplate2025winter.ui.screens.settings
import androidx.compose.ui.graphics.Color

data class ColorOption(val color: Color, val name: String)
data class SettingsUiState(
    val backgroundColorOptions: List<ColorOption> = listOf(
        ColorOption(Color(0xFF112117), "Deep Green"),
        ColorOption(Color(0xFF000000), "Black"),
        ColorOption(Color(0xFF1B3224), "Forest"),
        ColorOption(Color(0xFF2C3E50), "Navy"),
        ColorOption(Color(0xFF4A2C2C), "Dark Red")
    ),
    val selectedBackgroundColor: ColorOption = ColorOption(Color(0xFF1B3224), "Forest"),
    val textColorOptions: List<ColorOption> = listOf(
        ColorOption(Color.White, "White"),
        ColorOption(Color(0xFF36E27B), "Neon Green"),
        ColorOption(Color(0xFFF1C40F), "Yellow"),
        ColorOption(Color(0xFFE67E22), "Orange"),
        ColorOption(Color(0xFF9B59B6), "Purple")
    ),
    val selectedTextColor: ColorOption = ColorOption(Color.White, "White"),
    val timeSize: Float = 64f,
    val dateSize: Float = 18f,
    val isLoading: Boolean = false,
    val error: String? = null
)
