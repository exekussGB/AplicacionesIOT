package com.example.aplicacionesiot.data.remote.dto

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
data class RegisterResponse(
    val success: Boolean,
    val message: String
)
