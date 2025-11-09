package com.nadiaguerra.examenpractiquita.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadiaguerra.examenpractiquita.data.models.FormDataClass
import com.nadiaguerra.examenpractiquita.data.models.repository.FormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//configuracion de la base de datos
@HiltViewModel
class FormViewModel @Inject constructor(private val repository: FormRepository): ViewModel() {

    private val _formList = MutableStateFlow<List<FormDataClass>>(emptyList())
    val formList = _formList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllForms().collect { item ->
                if(item.isEmpty()){
                    _formList.value = emptyList()
                } else{
                    _formList.value = item
                }
            }
        }
    }

    fun addForm(form: FormDataClass) = viewModelScope.launch{repository.addForm(form)}
    fun updateForm(form: FormDataClass) = viewModelScope.launch{repository.updateForm(form)}
    fun deleteForm(form: FormDataClass) = viewModelScope.launch{repository.deleteForm(form)}

}