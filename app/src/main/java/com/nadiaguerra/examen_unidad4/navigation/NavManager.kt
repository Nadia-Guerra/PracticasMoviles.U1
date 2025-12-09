package com.nadiaguerra.examen_unidad4.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel
import com.nadiaguerra.examen_unidad4.views.DetailsView
import com.nadiaguerra.examen_unidad4.views.HomeView

@Composable
fun NavManager(viewModel: MoviesViewModel){
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = "Home"){
        composable("Home"){
            HomeView(viewModel, navController)
        }

        composable("DetailsView/{imdbID}", arguments = listOf(
            navArgument("imdbID"){type = NavType.StringType}
        )){
            val imdbID = it.arguments?.getString("imdbID") ?: ""
            DetailsView(viewModel, navController, imdbID)
        }
    }
}