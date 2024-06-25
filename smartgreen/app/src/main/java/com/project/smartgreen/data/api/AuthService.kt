package com.project.smartgreen.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.project.smartgreen.data.model.*
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {


    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("admins/add/user")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("crops/get")
    suspend fun getCrops(@Header("Authorization") token: String): Response<List<Crop>>

    @POST("crops/add")
    suspend fun addCrop(
        @Header("Authorization") token: String,
        @Body crop: Crop
    ): Response<Crop>





}