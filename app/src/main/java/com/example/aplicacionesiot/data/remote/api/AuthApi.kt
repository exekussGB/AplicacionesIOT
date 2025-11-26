package com.example.aplicacionesiot.data.remote.api

import com.example.aplicacionesiot.data.remote.dto.LoginRequest
import com.example.aplicacionesiot.data.remote.dto.LoginResponse
import com.example.aplicacionesiot.data.remote.dto.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("profile")
    suspend fun profile(
        @Header("Authorization") auth: String
    ): UserDto
}
