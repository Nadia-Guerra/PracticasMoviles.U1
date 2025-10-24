package com.nadiaguerra.coroutinesappa.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {Text("Dashboard")})
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("Coroutine")
                }
            ) {
                Text(text = "Ir")
            }
        }
    ) { paddingValues -> // se le agrega este bloquesito porque si no te pone un error de cagada de content al parecer
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
        }
    }
}