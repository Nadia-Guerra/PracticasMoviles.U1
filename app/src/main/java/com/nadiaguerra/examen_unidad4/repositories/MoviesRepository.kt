package com.nadiaguerra.examen_unidad4.repositories

import com.nadiaguerra.examen_unidad4.data.ApiMovies
import com.nadiaguerra.examen_unidad4.data.FavoriteMovieDao
import com.nadiaguerra.examen_unidad4.models.FavoriteMovie
import com.nadiaguerra.examen_unidad4.models.MovieDetails
import com.nadiaguerra.examen_unidad4.models.MovieItem
import com.nadiaguerra.examen_unidad4.util.Constants
import com.nadiaguerra.examen_unidad4.util.Constants.Companion.API_KEY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val apiMovies: ApiMovies,
    private val favoriteMovieDao: FavoriteMovieDao
) {
    suspend fun getMovies(page:Int = 1): List<MovieItem>?{
        val response = apiMovies.getMovies("movie",page, Constants.API_KEY)
        if(response.isSuccessful){
            return response.body()?.Search
        }
        return null
    }

    suspend fun getMovieById(imdbID: String): MovieDetails? {
        val response = apiMovies.getMovieById(imdbID)
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }

    suspend fun searchMovies(query: String, page:Int = 1): List<MovieItem>? {
        val response = apiMovies.getMovies(query,page, Constants.API_KEY)
        if(response.isSuccessful){
            return response.body()?.Search
        }
        return null
    }

    fun getAllFavorites(): Flow<List<FavoriteMovie>> {
        return favoriteMovieDao.getAllFavorites()
    }

    suspend fun addFavorite(movie: FavoriteMovie) {
        favoriteMovieDao.insertFavorite(movie)
    }

    suspend fun removeFavorite(imdbID: String) {
        favoriteMovieDao.deleteFavoriteById(imdbID)
    }

    suspend fun isFavorite(imdbID: String): Boolean {
        return favoriteMovieDao.isFavorite(imdbID)
    }
}