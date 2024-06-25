package com.project.smartgreen.utils


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.project.smartgreen.data.api.AuthService

object RetrofitInstance {

    private const val BASE_URL = "api.smartgreen.works"


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}