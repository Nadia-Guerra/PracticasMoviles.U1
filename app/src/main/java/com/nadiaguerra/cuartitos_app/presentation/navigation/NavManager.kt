package com.nadiaguerra.cuartitos_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadiaguerra.cuartitos_app.presentation.viewmodels.StudentViewModel
import com.nadiaguerra.cuartitos_app.presentation.views.*

@Composable
fun NavManager(viewModel: StudentViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ) {
        composable("Dashboard") {
            DashboardView(navController, viewModel)
        }
        composable("AddStudent") {
            AddStudentView(navController, viewModel)
        }
        composable(
            "Details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            val id = it.arguments?.getLong("id") ?: 0L
            DetailsView(navController, viewModel, id)
        }
    }
}

