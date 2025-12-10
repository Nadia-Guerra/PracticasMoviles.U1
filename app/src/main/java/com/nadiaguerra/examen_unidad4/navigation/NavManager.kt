package com.nadiaguerra.examen_unidad4.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel
import com.nadiaguerra.examen_unidad4.views.DetailsView
import com.nadiaguerra.examen_unidad4.views.FavoritesView
import com.nadiaguerra.examen_unidad4.views.HomeView
import com.nadiaguerra.examen_unidad4.views.SearchView

@Composable
fun NavManager(viewModel: MoviesViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf("Home", "Search", "Favorites")
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ) {
                    val items = listOf(
                        BottomNavItem.Home,
                        BottomNavItem.Search,
                        BottomNavItem.Favorites
                    )

                    items.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        // Evitar múltiples copias de la misma pantalla
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFFFFD700),
                                selectedTextColor = Color(0xFFFFD700),
                                unselectedIconColor = Color.Gray,
                                unselectedTextColor = Color.Gray,
                                indicatorColor = Color(0xFF2C2C2E)
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("Home") {
                HomeView(viewModel, navController)
            }

            composable("Search") {
                SearchView(viewModel, navController)
            }

            composable("Favorites") {
                FavoritesView(viewModel, navController)
            }

            composable(
                "DetailsView/{imdbID}",
                arguments = listOf(
                    navArgument("imdbID") { type = NavType.StringType }
                )
            ) {
                val imdbID = it.arguments?.getString("imdbID") ?: ""
                DetailsView(viewModel, navController, imdbID)
            }
        }
    }
}