package com.example.apptemplate2025winter.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val uiState by viewModel.uiState.collectAsState()
    SettingsScreenContent(
        uiState = uiState,
        onBackgroundColorSelected = viewModel::onBackgroundColorSelected,
        onTextColorSelected = viewModel::onTextColorSelected,
        onTimeSizeChange = viewModel::onTimeSizeChange,
        onDateSizeChange = viewModel::onDateSizeChange,
        onSaveClick = viewModel::saveSettings,
        onNavigateUp = onNavigateUp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    uiState: SettingsUiState,
    onBackgroundColorSelected: (ColorOption) -> Unit,
    onTextColorSelected: (ColorOption) -> Unit,
    onTimeSizeChange: (Float) -> Unit,
    onDateSizeChange: (Float) -> Unit,
    onSaveClick: () -> Unit,
    onNavigateUp: () -> Unit
) {
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
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                PreviewCard(
                    backgroundColor = uiState.selectedBackgroundColor.color,
                    textColor = uiState.selectedTextColor.color,
                    timeSize = uiState.timeSize,
                    dateSize = uiState.dateSize
                )

                Spacer(modifier = Modifier.height(16.dp))

                ColorSection(
                    title = "背景色",
                    colors = uiState.backgroundColorOptions,
                    selectedColor = uiState.selectedBackgroundColor,
                    onColorSelected = onBackgroundColorSelected
                )

                ColorSection(
                    title = "文字色",
                    colors = uiState.textColorOptions,
                    selectedColor = uiState.selectedTextColor,
                    onColorSelected = onTextColorSelected
                )

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )

                SizeSlider(
                    label = "時刻のサイズ",
                    value = uiState.timeSize,
                    onValueChange = onTimeSizeChange,
                    range = 12f..120f,
                    unit = "pt"
                )

                SizeSlider(
                    label = "日付のサイズ",
                    value = uiState.dateSize,
                    onValueChange = onDateSizeChange,
                    range = 10f..48f,
                    unit = "pt"
                )
            }


            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "設定を保存",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PreviewCard(
    backgroundColor: Color,
    textColor: Color,
    timeSize: Float,
    dateSize: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "PREVIEW",
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.25.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "12:34",
                color = textColor,
                fontSize = timeSize.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = timeSize.sp * 1.1
            )
            Text(
                text = "10月24日 月曜日",
                color = MaterialTheme.colorScheme.primary,
                fontSize = dateSize.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ColorSection(
    title: String,
    colors: List<ColorOption>,
    selectedColor: ColorOption,
    onColorSelected: (ColorOption) -> Unit
) {
    Column {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(colors) { colorOption ->
                ColorItem(
                    color = colorOption.color,
                    isSelected = colorOption == selectedColor,
                    onClick = { onColorSelected(colorOption) }
                )
            }
        }
    }
}

@Composable
fun ColorItem(color: Color, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = CircleShape
            )
            .clickable(onClick = onClick)
    )
}

@Composable
fun SizeSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    range: ClosedFloatingPointRange<Float>,
    unit: String
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${value.toInt()}$unit",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.size(24.dp), // Icon default size
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "-",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
            }
            Slider(
                value = value,
                onValueChange = onValueChange,
                valueRange = range,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.primary,
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                )
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase size",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AppTemplate2025WinterTheme(darkTheme = true) {
        SettingsScreenContent(
            uiState = SettingsUiState(),
            onBackgroundColorSelected = {},
            onTextColorSelected = {},
            onTimeSizeChange = {},
            onDateSizeChange = {},
            onSaveClick = {},
            onNavigateUp = {}
        )
    }
}
