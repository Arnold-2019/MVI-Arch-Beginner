package com.example.mvi_arch_beginner.data.api

import com.example.mvi_arch_beginner.data.model.User

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}
