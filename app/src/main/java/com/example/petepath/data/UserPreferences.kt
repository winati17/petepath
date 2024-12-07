package com.example.petepath.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

// Inisialisasi DataStore sebagai ekstensi pada Context
val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

// Definisikan Keys untuk Username, Email, dan Password
val USERNAME_KEY = stringPreferencesKey("username")
val EMAIL_KEY = stringPreferencesKey("email")
val PASSWORD_KEY = stringPreferencesKey("password")

// New key untuk history
val USER_HISTORY_KEY = stringPreferencesKey("user_history")