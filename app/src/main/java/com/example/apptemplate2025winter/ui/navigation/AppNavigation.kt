package com.example.apptemplate2025winter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptemplate2025winter.ui.screens.main.MainScreen

/**
 * アプリ全体のナビゲーション設定
 *
 * 新しい画面を追加する際は:
 * 1. NavigationDestination.kt に画面定義を追加
 * 2. このファイルの NavHost 内に composable を追加
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Main.route
    ) {
        // メイン画面
        composable(route = Main.route) {
            MainScreen()
        }
    }
}
