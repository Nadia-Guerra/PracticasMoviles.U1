package com.nadiaguerra.examenpractiquita.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FormDataStore(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserData")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_NOMBRE = stringPreferencesKey("user_nombre")
        val USER_APELLIDO = stringPreferencesKey("user_apellido")
    }

    //con estos get se mandan a llamar
    val getEmail: Flow<String> = context.dataStore.data
        .map{preferences -> preferences[USER_EMAIL] ?: ""}

    val getNombre: Flow<String> = context.dataStore.data
        .map{preferences -> preferences[USER_NOMBRE] ?: ""}

    val getApellido: Flow<String> = context.dataStore.data
        .map{preferences -> preferences[USER_APELLIDO] ?: ""}

    //uso de las corrutinas, aqui se guardan
    suspend fun saveUser(nombre: String, apellido: String, email: String){
        context.dataStore.edit{preferences ->
            preferences[USER_EMAIL] = email
            preferences[USER_NOMBRE] = nombre
            preferences[USER_APELLIDO] = apellido
        }
    }
}