package com.example.apptemplate2025winter.ui.navigation

/**
 * 画面遷移先を定義するインターフェース
 */
interface NavigationDestination {
    val route: String
}

/**
 * メイン画面
 */
object Main : NavigationDestination {
    override val route = "main"
}
