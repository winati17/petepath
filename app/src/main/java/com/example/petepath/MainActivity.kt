package com.example.petepath

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.petepath.ui.theme.PetePathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetePathTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, context = this)
            }
        }
    }
}