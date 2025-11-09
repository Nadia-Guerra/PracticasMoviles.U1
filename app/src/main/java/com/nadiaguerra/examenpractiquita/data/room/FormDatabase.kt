package com.nadiaguerra.examenpractiquita.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nadiaguerra.examenpractiquita.data.models.FormDataClass

//no puede ser instanciada pero sus metodos si

@Database(entities = [FormDataClass::class], version = 1, exportSchema = false)
abstract class FormDatabase: RoomDatabase() {

    //va a apuntar al Dao que acabamos de crear
    abstract fun FormDao(): FormDataBaseDao //en un archivo aparte vamos a inicializar la base de datos

}