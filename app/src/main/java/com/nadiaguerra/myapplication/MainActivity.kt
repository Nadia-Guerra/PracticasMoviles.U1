package com.nadiaguerra.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nadiaguerra.myapplication.presentation.navigation.NavManager
import com.nadiaguerra.myapplication.presentation.views.HomeView
import com.nadiaguerra.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContent { //todo lo que situemos dentro seran Composables
            MyApplicationTheme{
                NavManager()
            }

        }
    }
}



