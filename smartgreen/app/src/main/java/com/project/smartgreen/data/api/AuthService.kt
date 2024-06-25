package com.project.smartgreen.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.project.smartgreen.data.model.*

interface AuthService {


    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("admins/add/user")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}