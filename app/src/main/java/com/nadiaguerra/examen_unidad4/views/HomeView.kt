package com.nadiaguerra.examen_unidad4.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadiaguerra.examen_unidad4.components.CardMovie
import com.nadiaguerra.examen_unidad4.components.MainTopBar
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: MoviesViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()
    val favoriteStatus by viewModel.favoriteStatus.collectAsState()
    val gridState = rememberLazyGridState()

    // Verificar el estado de favoritos para las películas visibles
    LaunchedEffect(movies) {
        movies.forEach { movie ->
            viewModel.checkFavoriteStatus(movie.imdbID)
        }
    }

    // ✅ Detectar cuando llegamos al final para cargar más (scroll infinito)
    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null && lastVisibleIndex >= movies.size - 4) {
                    // Aquí puedes cargar más películas si implementas paginación
                    // Por ahora, la API de OMDB tiene limitaciones para scroll infinito real
                }
            }
    }

    Scaffold(
        topBar = {
            MainTopBar(title = "Películas", showBackButton = false, onClickBackButton = {})
        }
    ) { padding ->
        if (movies.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFF1C1C1E)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFFFFD700))
            }
        } else {
            // ✅ Grid de 4 columnas
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columnas para móvil (ajusta a 4 si es tablet)
                state = gridState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFF1C1C1E))
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(movies) { movie ->
                    CardMovie(
                        movie = movie,
                        isFavorite = favoriteStatus[movie.imdbID] ?: false,
                        onFavoriteClick = {
                            viewModel.toggleFavorite(movie)
                        },
                        onClick = {
                            navController.navigate("DetailsView/${movie.imdbID}")
                        }
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color(0xFFFFD700)
                        )
                    }
                }
            }
        }
    }
}