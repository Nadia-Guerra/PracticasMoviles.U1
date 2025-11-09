package com.nadiaguerra.examenpractiquita.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeDataStore (private val context: Context){
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ThemePreferences")
        val THEME_PREFERENCES = booleanPreferencesKey("theme_preferences")

    }
    val ThemePreferences: Flow<Boolean> = context.dataStore.data
        .map{preferences -> preferences[THEME_PREFERENCES] ?: false}


    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_PREFERENCES] = isDark
        }
    }
}