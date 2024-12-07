package com.example.petepath.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

data class UserPreferences(
    val username: String?,
    val email: String?,
    val password: String?
)

class UserPreferencesRepository(private val context: Context) {

    // Membuat instance Json dengan konfigurasi yang diperlukan
    private val json = Json { ignoreUnknownKeys = true }

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

    // Fungsi untuk menambahkan item riwayat
    suspend fun addHistoryItem(item: DataHistoryItem) {
        context.dataStore.edit { preferences ->
            val currentHistoryJson = preferences[USER_HISTORY_KEY]
            val currentHistory: MutableList<DataHistoryItem> = if (currentHistoryJson != null) {
                try {
                    // Spesifikasikan tipe generik secara eksplisit
                    json.decodeFromString<List<DataHistoryItem>>(currentHistoryJson).toMutableList()
                } catch (e: Exception) {
                    // Jika terjadi kesalahan saat mendekode, mulai dengan daftar kosong
                    mutableListOf()
                }
            } else {
                mutableListOf()
            }
            // Tambahkan item baru ke riwayat
            currentHistory.add(item)
            // Opsional: Batasi jumlah riwayat menjadi maksimal 10
            if (currentHistory.size > 10) {
                currentHistory.removeAt(0)
            }
            // Simpan kembali riwayat yang telah diperbarui sebagai string JSON
            preferences[USER_HISTORY_KEY] = json.encodeToString(currentHistory)
        }
    }

    // Fungsi untuk menghapus riwayat
    suspend fun clearHistory() {
        context.dataStore.edit { preferences ->
            preferences.remove(USER_HISTORY_KEY)
        }
    }
}