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

class UserViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    val userPreferences: StateFlow<UserPreferences> = repository.userPreferencesFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, UserPreferences(null, null, null))

    val userHistory: StateFlow<List<DataHistoryItem>> = repository.userHistoryFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun saveUserData(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.saveUserData(username, email, password)
        }
    }

    suspend fun login(emailInput: String, passwordInput: String): Boolean {
        val userPreferences = userPreferences.first()
        return emailInput == userPreferences.email && passwordInput == userPreferences.password
    }

    // Fungsi logout untuk menghapus data pengguna
    fun clearUser() {
        viewModelScope.launch {
            repository.clearUserData()
        }
    }

    // Fungsi untuk menambahkan history item
    fun addHistoryItem(item: DataHistoryItem) {
        viewModelScope.launch {
            repository.addHistoryItem(item)
        }
    }

    // Fungsi untuk menghapus history
    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
        }
    }
}
