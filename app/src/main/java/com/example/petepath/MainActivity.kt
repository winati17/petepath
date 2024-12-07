package com.example.petepath

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White) // Mengatur latar belakang putih
            ) {
                val context = androidx.compose.ui.platform.LocalContext.current
                SetupNavGraph(navController = navController, context = context)
            }
        }
    }
}
