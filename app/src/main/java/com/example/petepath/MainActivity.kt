package com.example.petepath

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Log.d("NavController", "Navigation Graph set up. Current destination: ${navController.currentDestination?.route}")
            SetupNavGraph(navController = navController)
        }
    }
}