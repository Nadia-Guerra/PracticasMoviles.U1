package com.nadiaguerra.examenpractiquita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.nadiaguerra.examenpractiquita.data.ThemeDataStore
import com.nadiaguerra.examenpractiquita.presentation.navigation.NavManager
import com.nadiaguerra.examenpractiquita.ui.theme.ExamenPractiquitaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            val themeDataStore = remember { ThemeDataStore(context) }
            val isDarkTheme by themeDataStore.ThemePreferences.collectAsState(initial = false)

            ExamenPractiquitaTheme(darkTheme = isDarkTheme) {
                NavManager()
            }
        }
    }
}




