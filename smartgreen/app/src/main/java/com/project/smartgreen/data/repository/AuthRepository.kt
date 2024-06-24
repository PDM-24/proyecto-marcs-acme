package com.project.smartgreen.data.repository


import com.project.smartgreen.data.api.AuthService
import com.project.smartgreen.data.model.*
import retrofit2.Response

class AuthRepository(private val authService: AuthService) {
    suspend fun login(username: String, password: String, role: String): Response<LoginResponse> {
        return authService.login(LoginRequest(username, password, role))
    }

    suspend fun register(username: String, password: String): Response<RegisterResponse> {
        return authService.register(RegisterRequest(username, password, "user"))
    }
}