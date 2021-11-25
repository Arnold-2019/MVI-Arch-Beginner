package com.example.mvi_arch_beginner.data.api

import com.example.mvi_arch_beginner.data.model.User

interface ApiHelper {
    suspend fun getUsers(): List<User>
}
