package com.example.aplicacionesiot.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplicacionesiot.data.local.StoreUser
import com.example.aplicacionesiot.data.repository.AuthRepository

class AuthViewModelFactory(private val repo: AuthRepository, private val storeUser: StoreUser) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repo, storeUser) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
