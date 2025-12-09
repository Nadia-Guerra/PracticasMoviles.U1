package com.nadiaguerra.examen_unidad4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

}