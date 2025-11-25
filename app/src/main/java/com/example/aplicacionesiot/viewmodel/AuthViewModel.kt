package com.example.aplicacionesiot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplicacionesiot.data.local.StoreUser
import com.example.aplicacionesiot.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository, private val storeUser: StoreUser) : ViewModel() {

    fun register(name: String, email: String, pass: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        viewModelScope.launch {
            val res = repo.register(name, email, pass)
            res.fold(
                onSuccess = { onSuccess() },
                onFailure = { onFail(it.message ?: "Error al registrar") }
            )
        }
    }

    fun login(email: String, pass: String, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        viewModelScope.launch {
            val res = repo.login(email, pass)
            res.fold(
                onSuccess = { 
                    val token = it.token
                    val userEmail = it.user?.email
                    if (token != null && userEmail != null) {
                        storeUser.saveToken(token)
                        storeUser.saveUserEmail(userEmail)
                    }
                    onSuccess() 
                },
                onFailure = { onFail(it.message ?: "Error al iniciar sesión") }
            )
        }
    }
}
