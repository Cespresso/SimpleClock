package com.example.apptemplate2025winter.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // アプリ全体で使用する依存関係をここに定義します
    // 例: DataStore, Retrofit, Room等のインスタンス
}
