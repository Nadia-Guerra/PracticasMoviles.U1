package com.nadiaguerra.examen_unidad4.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.nadiaguerra.examen_unidad4.models.MovieDetails
import com.nadiaguerra.examen_unidad4.models.MovieItem
import com.nadiaguerra.examen_unidad4.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MoviesRepository): ViewModel() {

    private val _movies = MutableStateFlow<List<MovieItem>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieDetails?>(null)
    val movieDetail = _movieDetail.asStateFlow()

    init{
        fetchMovies()
    }

    private fun fetchMovies(){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                val result = repo.getMovies()
                _movies.value = result ?: emptyList()
            }
        }
    }
    fun getMovieById(imdbID: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repo.getMovieById(imdbID)
                _movieDetail.value = result
            }
        }
    }
    fun clearMovieDetail() {
        _movieDetail.value = null
    }
}