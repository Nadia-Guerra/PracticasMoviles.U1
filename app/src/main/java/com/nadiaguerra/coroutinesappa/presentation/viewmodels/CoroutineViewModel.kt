package com.nadiaguerra.coroutinesappa.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineViewModel: ViewModel() {
    val result = mutableStateOf("")
    val isLoading = mutableStateOf(false    )
    fun callToApi(){
        viewModelScope.launch { //para ejecutar una funcion asincrona o suspendida
            call()
        }
    }

    suspend fun call(){
        isLoading.value = true
        result.value = withContext(Dispatchers.IO) { //esto antes estaba en el callToApi
            delay(5000)
            "Result"
        }
        isLoading.value = false
    }


    fun block(){
        Thread.sleep(10000)
    }
}