package com.nadiaguerra.examen_unidad4.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadiaguerra.examen_unidad4.components.CardMovie
import com.nadiaguerra.examen_unidad4.components.MainTopBar
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: MoviesViewModel, navController: NavController) {
    val movies by viewModel.movies.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar(title = "Películas", showBackButton = false, onClickBackButton = {})
        }
    ) { padding ->
        if (movies.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(movies) { movie ->
                    CardMovie(movie = movie) {
                        navController.navigate("DetailsView/${movie.imdbID}")
                    }
                }
            }
        }
    }
}