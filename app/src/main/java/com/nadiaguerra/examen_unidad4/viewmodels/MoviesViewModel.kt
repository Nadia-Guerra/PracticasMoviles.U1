package com.nadiaguerra.examen_unidad4.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadiaguerra.examen_unidad4.models.FavoriteMovie
import com.nadiaguerra.examen_unidad4.models.MovieDetails
import com.nadiaguerra.examen_unidad4.models.MovieItem
import com.nadiaguerra.examen_unidad4.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MoviesRepository): ViewModel() {

    private val _movies = MutableStateFlow<List<MovieItem>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _movieDetail = MutableStateFlow<MovieDetails?>(null)
    val movieDetail = _movieDetail.asStateFlow()

    private val _searchResults = MutableStateFlow<List<MovieItem>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val favorites = repo.getAllFavorites().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val _favoriteStatus = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val favoriteStatus = _favoriteStatus.asStateFlow()

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
                checkFavoriteStatus(imdbID)
            }
        }
    }

    fun clearMovieDetail() {
        _movieDetail.value = null
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            return
        }

        _isSearching.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = repo.searchMovies(query)
                _searchResults.value = result ?: emptyList()
                _isSearching.value = false
            }
        }
    }

    fun clearSearch() {
        _searchResults.value = emptyList()
    }

    fun toggleFavorite(movie: MovieItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isFav = repo.isFavorite(movie.imdbID)
                if (isFav) {
                    repo.removeFavorite(movie.imdbID)
                } else {
                    val favorite = FavoriteMovie(
                        imdbID = movie.imdbID,
                        title = movie.Title,
                        year = movie.Year,
                        poster = movie.Poster
                    )
                    repo.addFavorite(favorite)
                }
                checkFavoriteStatus(movie.imdbID)
            }
        }
    }

    fun removeFavorite(imdbID: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.removeFavorite(imdbID)
            }
        }
    }

    fun checkFavoriteStatus(imdbID: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val isFav = repo.isFavorite(imdbID)
                _favoriteStatus.value = _favoriteStatus.value + (imdbID to isFav)
            }
        }
    }
}