package com.example.petepath.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.serialization.Serializable

val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Serializable
data class UserPreferences(
    val username: String?,
    val email: String?,
    val password: String?
)

@Serializable
data class DataHistoryItem(
    val routeNumber: String,
    val routeName: String,
    val date: String
)

data class Route(
    val id: String,
    val name: String,
    val price: String
)

@Serializable
data class ReportItem(
    val routeNumber: String,
    val routeName: String,
    val violationCategory: String,
    val description: String,
    val vehiclePlate: String,
    val date: String
)