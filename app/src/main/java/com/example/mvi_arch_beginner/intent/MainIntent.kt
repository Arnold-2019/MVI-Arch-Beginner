package com.example.mvi_arch_beginner.intent

sealed class MainIntent {
    object FetchUser : MainIntent()
}
