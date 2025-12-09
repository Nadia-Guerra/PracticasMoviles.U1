package com.nadiaguerra.examen_unidad4.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nadiaguerra.examen_unidad4.components.MainTopBar
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(viewModel: MoviesViewModel, navController: NavController, imdbID: String) {

    LaunchedEffect(imdbID) {
        viewModel.getMovieById(imdbID)
    }

    val movieDetail by viewModel.movieDetail.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar(
                title = "Detalles",
                showBackButton = true,
                onClickBackButton = {
                    viewModel.clearMovieDetail()
                    navController.popBackStack()
                }
            )
        }
    ) { padding ->
        if (movieDetail == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val movie = movieDetail!!
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFF1C1C1E))
            ) {
                AsyncImage(
                    model = movie.Poster,
                    contentDescription = movie.Title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = movie.Title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = movie.Year,
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "⭐ ${movie.imdbRating}",
                            fontSize = 16.sp,
                            color = Color(0xFFFFD700)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Género
                    Text(
                        text = "Género: ${movie.Genre}",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Director
                    Text(
                        text = "Director: ${movie.Director}",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Actores: ${movie.Actors}",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Sinopsis",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.Plot,
                        fontSize = 14.sp,
                        color = Color.LightGray,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (movie.Ratings.isNotEmpty()) {
                        Text(
                            text = "Calificaciones",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        movie.Ratings.forEach { rating ->
                            Text(
                                text = "${rating.Source}: ${rating.Value}",
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}