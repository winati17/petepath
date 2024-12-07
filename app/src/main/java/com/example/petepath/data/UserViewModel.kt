package com.example.petepath

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun saveUserData(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.saveUserData(username, email, password)
        }
    }

    suspend fun login(emailInput: String, passwordInput: String): Boolean {
        val userPreferences = userPreferences.first()
        return emailInput == userPreferences.email && passwordInput == userPreferences.password
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearUserData()
        }
    }
}
