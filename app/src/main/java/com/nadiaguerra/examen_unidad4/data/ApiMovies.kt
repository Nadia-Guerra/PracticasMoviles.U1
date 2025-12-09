package com.nadiaguerra.examen_unidad4.data

import com.nadiaguerra.examen_unidad4.models.MoviesModel
import com.nadiaguerra.examen_unidad4.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiMovies {
    @GET(".")
    suspend fun getMovies(
        @Query("s") query: String? = null,
        @Query("apikey") apiKey: String = Constants.API_KEY
    ): Response<MoviesModel>

}
