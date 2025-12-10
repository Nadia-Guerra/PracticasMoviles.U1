package com.nadiaguerra.examen_unidad4.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nadiaguerra.examen_unidad4.models.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}