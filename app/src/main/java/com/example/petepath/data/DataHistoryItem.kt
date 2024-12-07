package com.example.petepath.data

import kotlinx.serialization.Serializable

@Serializable
data class DataHistoryItem(
    val routeNumber: String,
    val routeName: String,
    val date: String
)
