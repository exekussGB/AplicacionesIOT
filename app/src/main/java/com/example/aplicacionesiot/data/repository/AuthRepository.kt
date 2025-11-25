package com.example.aplicacionesiot.data.repository

import com.example.aplicacionesiot.data.remote.api.AuthApi
import com.example.aplicacionesiot.data.remote.dto.RegisterRequest

class AuthRepository(private val api: AuthApi) {
    suspend fun register(name: String, email: String, password: String): Result<String> {
        return try {
            val body = RegisterRequest(name, email, password)
            val response = api.register(body)
            if (!response.success) {
                return Result.failure(Exception(response.message))
            }
            Result.success(response.message)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
