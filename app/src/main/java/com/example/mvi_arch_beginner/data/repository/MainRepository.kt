package com.example.mvi_arch_beginner.data.repository

import com.example.mvi_arch_beginner.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}
