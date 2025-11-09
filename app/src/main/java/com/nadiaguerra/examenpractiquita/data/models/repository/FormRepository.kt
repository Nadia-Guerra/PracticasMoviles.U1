package com.nadiaguerra.examenpractiquita.data.models.repository

import com.nadiaguerra.examenpractiquita.data.models.FormDataClass
import com.nadiaguerra.examenpractiquita.data.room.FormDataBaseDao
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class FormRepository @Inject constructor(private val formDataBaseDao: FormDataBaseDao){
    suspend fun addForm(form: FormDataClass) = formDataBaseDao.insert(form)
    suspend fun updateForm(form: FormDataClass) = formDataBaseDao.update(form)
    suspend fun deleteForm(form: FormDataClass) = formDataBaseDao.delete(form)
    fun getAllForms(): Flow<List<FormDataClass>> = formDataBaseDao.getForms().flowOn(Dispatchers.IO).conflate()
    fun getFormById(id: Long): Flow<FormDataClass> = formDataBaseDao.getFormsById(id).flowOn(Dispatchers.IO).conflate()

}