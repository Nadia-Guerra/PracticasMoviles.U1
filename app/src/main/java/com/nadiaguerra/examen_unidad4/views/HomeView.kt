package com.nadiaguerra.examen_unidad4.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel

@Composable
fun HomeView(viewModel: MoviesViewModel){
    val movies by viewModel.movies.collectAsState()
    LazyColumn{
        items(movies){item ->
            Text(item.Title)
        }
    }
}