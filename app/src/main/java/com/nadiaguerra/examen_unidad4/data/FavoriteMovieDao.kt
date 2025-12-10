package com.nadiaguerra.examen_unidad4.data

import androidx.room.*
import com.nadiaguerra.examen_unidad4.models.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movies")
    fun getAllFavorites(): Flow<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movies WHERE imdbID = :imdbID")
    suspend fun getFavoriteById(imdbID: String): FavoriteMovie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movie: FavoriteMovie)

    @Delete
    suspend fun deleteFavorite(movie: FavoriteMovie)

    @Query("DELETE FROM favorite_movies WHERE imdbID = :imdbID")
    suspend fun deleteFavoriteById(imdbID: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE imdbID = :imdbID)")
    suspend fun isFavorite(imdbID: String): Boolean
}