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
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(movies) {
        movies.forEach { movie -> //va verificando si las pelis son favoritas o no
            viewModel.checkFavoriteStatus(movie.imdbID)
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow {
            val layoutInfo = gridState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

            lastVisibleItem >= totalItems - 4
        }.collect { shouldLoadMore ->
            if (shouldLoadMore && !isLoadingMore) {
                viewModel.loadMoreMovies()
            }
        }
    }

    Scaffold(
        topBar = {
            MainTopBar(title = "Películas", showBackButton = false, onClickBackButton = {})
        }
    ) { padding ->
        if (movies.isEmpty() && !isLoadingMore) {
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = gridState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFF1C1C1E))
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(movies, key = { it.imdbID }) { movie ->
                    CardMovie(
                        movie = movie,
                        isFavorite = favoriteStatus[movie.imdbID] ?: false,
                        onFavoriteClick = {
                            viewModel.changeFavorite(movie)
                        },
                        onClick = {
                            navController.navigate("DetailsView/${movie.imdbID}")
                        }
                    )
                }

                if (isLoadingMore) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp),
                                color = Color(0xFFFFD700)
                            )
                        }
                    }
                }
            }
        }
    }
}