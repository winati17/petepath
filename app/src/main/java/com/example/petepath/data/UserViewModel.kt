package com.example.petepath

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petepath.data.DataHistoryItem
import com.example.petepath.data.UserPreferences
import com.example.petepath.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest

class UserViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    val userPreferences: StateFlow<UserPreferences> = repository.userPreferencesFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, UserPreferences(null, null, null))

    // Mengambil riwayat pengguna berdasarkan email dari preferensi
    val userHistory: StateFlow<List<DataHistoryItem>> = userPreferences
        .flatMapLatest { prefs ->
            repository.getUserHistoryFlow(prefs.email)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun saveUserData(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.saveUserData(username, email, password)
        }
    }

    suspend fun login(usernameInput: String, passwordInput: String): Boolean {
        val userPreferences = userPreferences.first()
        return usernameInput == userPreferences.username && passwordInput == userPreferences.password
    }

    // Fungsi logout untuk menghapus data pengguna
    fun clearUser() {
        viewModelScope.launch {
            repository.clearUserData()
        }
    }

    // Fungsi untuk menambahkan riwayat
    fun addHistoryItem(item: DataHistoryItem) {
        viewModelScope.launch {
            val email = userPreferences.value.email
            if (email != null) {
                repository.addHistoryItem(email, item)
            }
        }
    }

    // Fungsi untuk menghapus history
    fun clearHistory() {
        viewModelScope.launch {
            val email = userPreferences.value.email
            if (email != null) {
                repository.clearHistory(email)
            }
        }
    }
}
