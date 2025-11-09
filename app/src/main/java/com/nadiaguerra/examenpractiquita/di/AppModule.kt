package com.nadiaguerra.examenpractiquita.data.di

import android.content.Context
import androidx.room.Room
import com.nadiaguerra.examenpractiquita.data.room.FormDatabase
import com.nadiaguerra.examenpractiquita.data.room.FormDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFormDao(formDatabase: FormDatabase): FormDataBaseDao {
        return formDatabase.FormDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FormDatabase {
        return Room.databaseBuilder(
            context,
            FormDatabase::class.java,
            "forms_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
