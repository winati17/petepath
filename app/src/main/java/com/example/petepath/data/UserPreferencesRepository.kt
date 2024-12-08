package com.example.petepath.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString

class UserPreferencesRepository(private val context: Context) {

    private val json = Json { ignoreUnknownKeys = true }

    private val USERS_KEY = stringPreferencesKey("users_list")

    // Fungsi untuk mengambil alur daftar pengguna
    val usersFlow: Flow<List<UserPreferences>> = context.dataStore.data.map { preferences ->
        val usersJson = preferences[USERS_KEY]
        if (usersJson != null) {
            try {
                json.decodeFromString<List<UserPreferences>>(usersJson)
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // Fungsi untuk menambahkan pengguna baru
    suspend fun addUser(user: UserPreferences) {
        context.dataStore.edit { preferences ->
            val currentUsersJson = preferences[USERS_KEY]
            val currentUsers: MutableList<UserPreferences> = if (currentUsersJson != null) {
                try {
                    json.decodeFromString<List<UserPreferences>>(currentUsersJson).toMutableList()
                } catch (e: Exception) {
                    mutableListOf()
                }
            } else {
                mutableListOf()
            }

            currentUsers.add(user)
            preferences[USERS_KEY] = json.encodeToString(currentUsers)
        }
    }

    // Fungsi untuk mengambil pengguna berdasarkan username atau email
    suspend fun getUser(username: String?, email: String?): UserPreferences? {
        val users = usersFlow.first()
        return users.find { it.username.equals(username, ignoreCase = true) || it.email.equals(email, ignoreCase = true) }
    }

    // Fungsi untuk mendapatkan semua pengguna
    suspend fun getAllUsers(): List<UserPreferences> {
        return usersFlow.first()
    }

    // Fungsi untuk menghapus semua pengguna (opsional)
    suspend fun clearAllUsers() {
        context.dataStore.edit { preferences ->
            preferences.remove(USERS_KEY)
        }
    }

    // Fungsi untuk mengambil riwayat pengguna berdasarkan email
    private fun getUserHistoryKey(email: String): Preferences.Key<String> {
        return stringPreferencesKey("user_history_$email")
    }

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

    suspend fun clearHistory(email: String) {
        context.dataStore.edit { preferences ->
            if (email.isNotEmpty()) {
                preferences.remove(getUserHistoryKey(email))
            }
        }
    }

    // Fungsi untuk mengambil riwayat laporan pengguna berdasarkan email
    private fun getUserReportsKey(email: String): Preferences.Key<String> {
        return stringPreferencesKey("user_reports_$email")
    }

    fun getUserReportsFlow(email: String?): Flow<List<ReportItem>> {
        return if (email != null) {
            context.dataStore.data.map { preferences ->
                val reportsJson = preferences[getUserReportsKey(email)]
                if (reportsJson != null) {
                    try {
                        json.decodeFromString<List<ReportItem>>(reportsJson)
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

    suspend fun addReportItem(email: String, report: ReportItem) {
        context.dataStore.edit { preferences ->
            if (email.isNotEmpty()) {
                val reportsKey = getUserReportsKey(email)
                val currentReportsJson = preferences[reportsKey]
                val currentReports: MutableList<ReportItem> = if (currentReportsJson != null) {
                    try {
                        json.decodeFromString<List<ReportItem>>(currentReportsJson).toMutableList()
                    } catch (e: Exception) {
                        mutableListOf()
                    }
                } else {
                    mutableListOf()
                }
                currentReports.add(report)
                // Batasi jumlah laporan menjadi maksimal 50
                if (currentReports.size > 50) {
                    currentReports.removeAt(0)
                }
                preferences[reportsKey] = json.encodeToString(currentReports)
            }
        }
    }

    suspend fun clearReports(email: String) {
        context.dataStore.edit { preferences ->
            if (email.isNotEmpty()) {
                preferences.remove(getUserReportsKey(email))
            }
        }
    }

    // Fungsi untuk mendapatkan semua laporan untuk pengguna
    suspend fun getAllReports(email: String): List<ReportItem> {
        return getUserReportsFlow(email).first()
    }
}