package com.example.petepath.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val username: String?,
    val email: String?,
    val password: String?
)

class UserPreferencesRepository(private val context: Context) {

    // Fungsi untuk menyimpan data user
    suspend fun saveUserData(username: String, email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
        }
    }

    // Fungsi untuk menghapus data user
    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    // Fungsi untuk mengambil data user
    val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data.map { preferences ->
        val username = preferences[USERNAME_KEY]
        val email = preferences[EMAIL_KEY]
        val password = preferences[PASSWORD_KEY]
        UserPreferences(username, email, password)
    }
}