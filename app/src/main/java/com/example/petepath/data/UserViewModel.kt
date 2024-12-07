package com.example.petepath

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petepath.data.UserPreferences
import com.example.petepath.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserPreferencesRepository) : ViewModel() {
    // Mendapatkan data user sebagai StateFlow
    val userPreferences: StateFlow<UserPreferences> = repository.userPreferencesFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, UserPreferences(null, null, null))

    // Fungsi untuk menyimpan data user
    fun saveUserData(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.saveUserData(username, email, password)
        }
    }
}