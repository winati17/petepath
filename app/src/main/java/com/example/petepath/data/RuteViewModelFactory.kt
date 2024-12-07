package com.example.petepath.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RuteViewModelFactory(private val ruteId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RuteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RuteViewModel(ruteId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
