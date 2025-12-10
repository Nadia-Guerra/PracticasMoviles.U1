package com.nadiaguerra.examen_unidad4.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem("Home", Icons.Default.Home, "Inicio")
    object Search : BottomNavItem("Search", Icons.Default.Search, "Buscar")
    object Favorites : BottomNavItem("Favorites", Icons.Default.Favorite, "Favoritos")
}