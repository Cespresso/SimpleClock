package com.example.apptemplate2025winter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apptemplate2025winter.ui.navigation.AppNavigation
import com.example.apptemplate2025winter.ui.theme.AppTemplate2025WinterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTemplate2025WinterTheme {
                AppNavigation()
            }
        }
    }
}