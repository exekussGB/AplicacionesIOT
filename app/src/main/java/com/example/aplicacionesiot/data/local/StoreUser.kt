package com.example.aplicacionesiot.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class StoreUser(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    val getAccessToken: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[TOKEN_KEY]
    }

    val getUserEmail: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USER_EMAIL_KEY]
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    suspend fun saveUserEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_EMAIL_KEY] = email
        }
    }
    
    suspend fun clearData() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
