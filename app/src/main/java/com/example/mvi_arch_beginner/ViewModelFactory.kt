package com.example.mvi_arch_beginner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvi_arch_beginner.data.api.ApiHelper
import com.example.mvi_arch_beginner.data.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    @ExperimentalCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
