package com.nadiaguerra.examen_unidad4.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
fun SearchView(viewModel: MoviesViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val favoriteStatus by viewModel.favoriteStatus.collectAsState()

    // Verificar favoritos para resultados de búsqueda
    LaunchedEffect(searchResults) {
        searchResults.forEach { movie ->
            viewModel.checkFavoriteStatus(movie.imdbID)
        }
    }

    Scaffold(
        topBar = {
            MainTopBar(title = "Buscar", showBackButton = false, onClickBackButton = {})
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF1C1C1E))
        ) {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    if (it.length >= 3) {
                        viewModel.searchMovies(it)
                    } else if (it.isEmpty()) {
                        viewModel.clearSearch()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Buscar películas...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = {
                            searchQuery = ""
                            viewModel.clearSearch()
                        }) {
                            Icon(Icons.Default.Close, contentDescription = "Limpiar")
                        }
                    }
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF2C2C2E),
                    unfocusedContainerColor = Color(0xFF2C2C2E),
                    focusedIndicatorColor = Color(0xFFFFD700),
                    unfocusedIndicatorColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFFFFD700)
                )
            )

            // Resultados
            when {
                isSearching -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFFFD700))
                    }
                }
                searchQuery.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Escribe para buscar películas",
                            color = Color.Gray
                        )
                    }
                }
                searchResults.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No se encontraron resultados",
                            color = Color.Gray
                        )
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(searchResults) { movie ->
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
                    }
                }
            }
        }
    }
}
