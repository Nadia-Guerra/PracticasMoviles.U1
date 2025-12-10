package com.nadiaguerra.examen_unidad4.di

import android.content.Context
import androidx.room.Room
import com.nadiaguerra.examen_unidad4.data.ApiMovies
import com.nadiaguerra.examen_unidad4.data.FavoriteMovieDao
import com.nadiaguerra.examen_unidad4.data.MovieDatabase
import com.nadiaguerra.examen_unidad4.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides

    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideApiMovies(retrofit: Retrofit): ApiMovies {
        return retrofit.create(ApiMovies::class.java)

    }

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }
    @Singleton
    @Provides
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }
}