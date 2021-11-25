package com.example.mvi_arch_beginner.viewstate

import com.example.mvi_arch_beginner.data.model.User

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val users: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}
