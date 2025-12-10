package com.nadiaguerra.examen_unidad4.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovie(
    @PrimaryKey
    val imdbID: String,
    val title: String,
    val year: String,
    val poster: String
)