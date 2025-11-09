package com.nadiaguerra.examenpractiquita.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nadiaguerra.examenpractiquita.presentation.views.AddView
import com.nadiaguerra.examenpractiquita.presentation.views.DashboardView
import com.nadiaguerra.examenpractiquita.presentation.views.FormView
import com.nadiaguerra.examenpractiquita.presentation.views.ThemeView


@Composable
fun NavManager(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ){
        composable("Dashboard") {
            DashboardView(navController)
        }
        composable("Form") {
            FormView(navController)
        }
        composable("Add") {
            AddView(navController)
        }

        composable("Theme") {
            ThemeView(navController)
        }
    }
}
