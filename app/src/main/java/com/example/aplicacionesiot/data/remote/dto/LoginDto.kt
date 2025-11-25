package com.example.aplicacionesiot.data.remote.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String?,
    val token: String?,
    val user: UserDto?
)

data class UserDto(
    val id: Int,
    val name: String,
    val email: String
)
