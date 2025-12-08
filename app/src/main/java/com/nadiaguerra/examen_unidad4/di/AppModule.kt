package com.nadiaguerra.examen_unidad4.di

import com.nadiaguerra.examen_unidad4.data.ApiMovies
import com.nadiaguerra.examen_unidad4.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn
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
}