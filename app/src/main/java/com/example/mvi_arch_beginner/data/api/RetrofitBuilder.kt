package com.example.mvi_arch_beginner.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://test.mockapi.io/"
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}
