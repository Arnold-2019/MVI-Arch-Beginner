package com.example.mvi_arch_beginner.data.api

import com.example.mvi_arch_beginner.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}
