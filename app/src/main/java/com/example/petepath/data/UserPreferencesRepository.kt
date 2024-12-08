package com.example.petepath.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString


class UserPreferencesRepository(private val context: Context) {

    // Membuat instance Json dengan konfigurasi yang diperlukan
    private val json = Json { ignoreUnknownKeys = true }

    // Fungsi untuk menghasilkan kunci riwayat berdasarkan email pengguna
    private fun getUserHistoryKey(email: String): Preferences.Key<String> {
        return stringPreferencesKey("user_history_$email")
    }

    // Fungsi untuk mendapatkan alur riwayat pengguna berdasarkan email
    fun getUserHistoryFlow(email: String?): Flow<List<DataHistoryItem>> {
        return if (email != null) {
            context.dataStore.data.map { preferences ->
                val historyJson = preferences[getUserHistoryKey(email)]
                if (historyJson != null) {
                    try {
                        json.decodeFromString<List<DataHistoryItem>>(historyJson)
                    } catch (e: Exception) {
                        emptyList()
                    }
                } else {
                    emptyList()
                }
            }
        } else {
            flowOf(emptyList())
        }
    }

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

    // Fungsi untuk mengambil riwayat pengguna
    val userHistoryFlow: Flow<List<DataHistoryItem>> = context.dataStore.data.map { preferences ->
        val historyJson = preferences[USER_HISTORY_KEY]
        if (historyJson != null) {
            try {
                decodeFromString<List<DataHistoryItem>>(historyJson)
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // Fungsi untuk menambahkan item riwayat berdasarkan email
    suspend fun addHistoryItem(email: String, item: DataHistoryItem) {
        context.dataStore.edit { preferences ->
            if (email.isNotEmpty()) {
                val historyKey = getUserHistoryKey(email)
                val currentHistoryJson = preferences[historyKey]
                val currentHistory: MutableList<DataHistoryItem> = if (currentHistoryJson != null) {
                    try {
                        json.decodeFromString<List<DataHistoryItem>>(currentHistoryJson).toMutableList()
                    } catch (e: Exception) {
                        mutableListOf()
                    }
                } else {
                    mutableListOf()
                }
                currentHistory.add(item)
                // Batasi jumlah riwayat menjadi maksimal 10
                if (currentHistory.size > 10) {
                    currentHistory.removeAt(0)
                }
                preferences[historyKey] = json.encodeToString(currentHistory)
            }
        }
    }

    // Fungsi untuk menghapus riwayat berdasarkan email
    suspend fun clearHistory(email: String) {
        context.dataStore.edit { preferences ->
            if (email.isNotEmpty()) {
                preferences.remove(getUserHistoryKey(email))
            }
        }
    }
}