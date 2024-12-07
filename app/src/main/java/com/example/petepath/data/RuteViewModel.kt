package com.example.petepath.data

import androidx.lifecycle.ViewModel
import com.example.petepath.pages.features.getRouteNameById
import com.example.petepath.pages.features.getRoutesByRuteId

class RuteViewModel(val ruteId: String) : ViewModel() {
    val routeName: String = getRouteNameById(ruteId)
    val routesForRute: List<String> = getRoutesByRuteId(ruteId)

    // Add additional logic as needed
}
