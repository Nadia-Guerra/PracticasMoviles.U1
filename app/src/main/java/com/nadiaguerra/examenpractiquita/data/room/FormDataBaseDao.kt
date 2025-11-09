package com.nadiaguerra.examenpractiquita.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nadiaguerra.examenpractiquita.data.models.FormDataClass
import kotlinx.coroutines.flow.Flow


//Interface -> Repositorio -> ViewModel -> View
@Dao //Data Access Observer
interface FormDataBaseDao {
    //Crud

    //muestra todo los datos
    @Query("SELECT * FROM forms") //este tiene que estar asociado a un metodo
    fun getForms(): Flow<List<FormDataClass>> // pq va a mostrar una lista, de tipo nuestra data class

    //muestra un form en especifico
    @Query("SELECT * FROM forms WHERE id =:id ") //inicialmente paparece en rojo pq aun no se ha creado
    fun getFormsById(id:Long):Flow<FormDataClass> //retorna un flow tipo nuestra DC

                //on conlicts es para resolver conflictos a la hora de guardar
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(form: FormDataClass)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(form:FormDataClass)

    @Delete
    suspend fun delete(form: FormDataClass)



}