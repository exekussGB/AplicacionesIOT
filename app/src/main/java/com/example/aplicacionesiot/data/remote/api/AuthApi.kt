package com.example.aplicacionesiot.data.remote.api

import com.example.aplicacionesiot.data.remote.dto.RegisterRequest
import com.example.aplicacionesiot.data.remote.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    
    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse

}
