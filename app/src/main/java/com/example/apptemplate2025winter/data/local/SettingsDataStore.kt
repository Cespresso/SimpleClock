package com.example.apptemplate2025winter.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    object PreferencesKeys {
        val BACKGROUND_COLOR = stringPreferencesKey("background_color")
        val TEXT_COLOR = stringPreferencesKey("text_color")
        val TIME_SIZE = floatPreferencesKey("time_size")
        val DATE_SIZE = floatPreferencesKey("date_size")
    }

    suspend fun saveSettings(
        backgroundColor: String,
        textColor: String,
        timeSize: Float,
        dateSize: Float
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.BACKGROUND_COLOR] = backgroundColor
            preferences[PreferencesKeys.TEXT_COLOR] = textColor
            preferences[PreferencesKeys.TIME_SIZE] = timeSize
            preferences[PreferencesKeys.DATE_SIZE] = dateSize
        }
    }

    val backgroundColor: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.BACKGROUND_COLOR] ?: "0xFF1B3224"
    }

    val textColor: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.TEXT_COLOR] ?: "0xFFFFFFFF"
    }

    val timeSize: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.TIME_SIZE] ?: 64f
    }

    val dateSize: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.DATE_SIZE] ?: 18f
    }
}
