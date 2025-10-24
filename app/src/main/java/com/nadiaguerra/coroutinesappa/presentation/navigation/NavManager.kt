package com.nadiaguerra.coroutinesappa.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nadiaguerra.coroutinesappa.presentation.viewmodels.CoroutineViewModel
import com.nadiaguerra.coroutinesappa.presentation.views.CoroutineView
import com.nadiaguerra.coroutinesappa.presentation.views.DashboardView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    val coroutineViewModel:CoroutineViewModel = viewModel() //se crea esto pa instanciar y pasar como parmetro

    NavHost(
        navController = navController,
        startDestination = "Dashboard"
    ){
        composable("Dashboard"){
            DashboardView(navController)
        }
        composable("Coroutine"){
            CoroutineView(navController, coroutineViewModel)
        }
    }
}