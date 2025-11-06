package com.nadiaguerra.practica.presentation.NavManager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nadiaguerra.practica.presentation.views.DetailsView
import com.nadiaguerra.practica.presentation.views.HomeView

/***
 * Project: CuartoA2
 * Package: com.danielflores.cuartoa2.presentation.NavManager
 * Created by Kevin Daniel Flores Nataren
 * File created at 10/October/2025 at 14:29
 * All rights reserved 2025.
 **/

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details") {
            DetailsView(navController)
        }
    }
}