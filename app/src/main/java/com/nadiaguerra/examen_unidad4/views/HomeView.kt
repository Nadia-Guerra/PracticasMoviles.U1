package com.nadiaguerra.examen_unidad4.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nadiaguerra.examen_unidad4.components.CardMovie
import com.nadiaguerra.examen_unidad4.components.MainTopBar
import com.nadiaguerra.examen_unidad4.viewmodels.MoviesViewModel

@Composable
fun HomeView(viewModel: MoviesViewModel){
    Scaffold(
        topBar = {
            MainTopBar(title = "IMDb", showBackButton = true) {

            }
        }

    ){
        ContentHomeView(viewModel, it)
    }
}

@Composable
fun ContentHomeView(viewModel: MoviesViewModel, pad: PaddingValues){
    val movies by viewModel.movies.collectAsState()
    LazyColumn(modifier = Modifier.padding(pad)){
        items(movies){item ->
            CardMovie(item){
                ///
            }
            Text(text = item.Title,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
                )
        }
    }
}