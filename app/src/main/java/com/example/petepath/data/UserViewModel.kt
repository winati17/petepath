package com.example.petepath

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petepath.data.DataHistoryItem
import com.example.petepath.data.ReportItem
import com.example.petepath.data.UserPreferences
import com.example.petepath.data.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class UserViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    // Alur daftar pengguna
    val users: StateFlow<List<UserPreferences>> = repository.usersFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Email pengguna saat ini
    private var _currentUserEmail = MutableStateFlow<String?>(null)
    val currentUserEmail: StateFlow<String?> = _currentUserEmail.asStateFlow()

    // Alur riwayat pengguna saat ini
    val userHistory: StateFlow<List<DataHistoryItem>> = _currentUserEmail
        .flatMapLatest { email ->
            if (email != null) {
                repository.getUserHistoryFlow(email)
            } else {
                flowOf(emptyList())
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Alur laporan pengguna saat ini
    val userReports: StateFlow<List<ReportItem>> = _currentUserEmail
        .flatMapLatest { email ->
            if (email != null) {
                repository.getUserReportsFlow(email)
            } else {
                flowOf(emptyList())
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Simpan data pengguna (daftar pengguna baru)
    fun saveUserData(username: String, email: String, password: String) {
        viewModelScope.launch {
            val newUser = UserPreferences(username, email, password)
            repository.addUser(newUser)
            // Set pengguna saat ini
            _currentUserEmail.value = email
        }
    }

    // Login pengguna dengan username atau email dan password
    suspend fun login(usernameOrEmail: String, passwordInput: String): Boolean {
        val user = repository.getUser(usernameOrEmail, usernameOrEmail)
        return if (user?.password == passwordInput) {
            // Set pengguna saat ini
            _currentUserEmail.value = user.email
            true
        } else {
            false
        }
    }

    // Logout pengguna
    fun logout() {
        viewModelScope.launch {
            // Hapus pengguna saat ini
            _currentUserEmail.value = null
        }
    }

    // Menambahkan riwayat pengguna
    fun addHistoryItem(item: DataHistoryItem) {
        viewModelScope.launch {
            val email = _currentUserEmail.value
            if (email != null) {
                repository.addHistoryItem(email, item)
            }
        }
    }

    // Menambahkan laporan pengguna
    fun addReport(report: ReportItem) {
        viewModelScope.launch {
            val email = _currentUserEmail.value
            if (email != null) {
                repository.addReportItem(email, report)
            }
        }
    }

    // Menghapus semua riwayat pengguna
    fun clearHistory() {
        viewModelScope.launch {
            val email = _currentUserEmail.value
            if (email != null) {
                repository.clearHistory(email)
            }
        }
    }

    // Menghapus semua laporan pengguna
    fun clearReports() {
        viewModelScope.launch {
            val email = _currentUserEmail.value
            if (email != null) {
                repository.clearReports(email)
            }
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            repository.clearUserData()
        }
    }

    // Mendapatkan semua pengguna
    fun getAllUsers(): Flow<List<UserPreferences>> {
        return repository.usersFlow
    }

    // Mengatur pengguna saat ini
    fun setCurrentUser(emailOrUsername: String) {
        viewModelScope.launch {
            val user = repository.getUser(emailOrUsername, emailOrUsername)
            _currentUserEmail.value = user?.email
        }
    }

    // Mendapatkan semua laporan pengguna
    fun getAllReports(): Flow<List<ReportItem>> {
        return userReports
    }
}
