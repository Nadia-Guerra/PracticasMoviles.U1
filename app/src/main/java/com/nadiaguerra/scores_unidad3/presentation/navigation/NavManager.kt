package com.nadiaguerra.scores_unidad3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nadiaguerra.scores_unidad3.presentation.views.AddView
import com.nadiaguerra.scores_unidad3.presentation.views.DashboardView

@Composable
fun NavController(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ){
        composable("Dashboard") {
            DashboardView(navController)
        }

        composable("Add") {
            AddView(navController)
        }

        composable("Theme") {
            EditView(navController)
        }
    }
}