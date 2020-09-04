package com.example.livedatacoroutines.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.example.livedatacoroutines.repository.MainRepository
import com.example.livedatacoroutines.utills.Resource
import com.example.livedatacoroutines.utills.Resource.Companion.loading
import kotlinx.coroutines.Dispatchers



class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}