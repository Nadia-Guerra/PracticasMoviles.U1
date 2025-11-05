package com.nadiaguerra.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadiaguerra.myapplication.presentation.views.DetailsView
import com.nadiaguerra.myapplication.presentation.views.HomeView

@Composable
fun NavManager(){                                   //o sea ir d una vista a otra
    val navController = rememberNavController() //es el punto o variable que funcionara para hacer toda la navegacion de mi aplicacion
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable("Home"){ //el composable hace q haya un ruteo pa otro lado
            HomeView(navController)
        }
        composable("Details/{id}", arguments = listOf(
            navArgument(name = "id"){
                type = NavType.LongType
            }
        )){
            val id = it.arguments?.getLong("id")?:0L
            DetailsView(navController, id)
        }
    }
}