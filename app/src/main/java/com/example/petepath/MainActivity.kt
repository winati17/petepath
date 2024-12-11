package com.example.petepath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.petepath.data.UserViewModelFactory
import com.example.petepath.ui.theme.PetePathTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userViewModel: UserViewModel =
            ViewModelProvider(this,
                UserViewModelFactory(this)
            )[UserViewModel::class.java]

        setContent {
            PetePathTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, viewModel = userViewModel)
            }
        }
    }
}