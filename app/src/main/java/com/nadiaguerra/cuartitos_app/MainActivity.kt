package com.nadiaguerra.cuartitos_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nadiaguerra.cuartitos_app.presentation.navigation.NavManager
import com.nadiaguerra.cuartitos_app.presentation.viewmodels.StudentViewModel
import com.nadiaguerra.cuartitos_app.ui.theme.Cuartitos_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = StudentViewModel()

        setContent {
            Cuartitos_AppTheme {
                NavManager(viewModel)
            }
        }
    }
}

