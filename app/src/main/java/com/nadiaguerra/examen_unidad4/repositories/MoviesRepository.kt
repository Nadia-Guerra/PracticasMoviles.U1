package com.nadiaguerra.examen_unidad4.repositories

import com.nadiaguerra.examen_unidad4.data.ApiMovies
import com.nadiaguerra.examen_unidad4.models.MovieDetails
import com.nadiaguerra.examen_unidad4.models.MovieItem
import com.nadiaguerra.examen_unidad4.models.MoviesModel
import com.nadiaguerra.examen_unidad4.util.Constants
import jakarta.inject.Inject

class MoviesRepository @Inject constructor(private val apiMovies: ApiMovies) {
    suspend fun getMovies(): List<MovieItem>?{
        val response = apiMovies.getMovies("movie", Constants.API_KEY)
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
}