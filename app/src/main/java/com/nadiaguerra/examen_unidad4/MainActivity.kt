package com.nadiaguerra.examen_unidad4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nadiaguerra.examen_unidad4.navigation.NavManager
import com.nadiaguerra.examen_unidad4.ui.theme.Examen_unidad4Theme
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel
import com.nadiaguerra.examen_unidad4.views.HomeView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MoviesViewModel by viewModels()
        setContent {
            Examen_unidad4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavManager(viewModel)
                }
            }
        }
    }
}
