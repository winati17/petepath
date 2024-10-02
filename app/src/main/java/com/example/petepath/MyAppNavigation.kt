package com.example.petepath

import LoginPage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petepath.pages.HomePage
//import com.example.petepath.pages.LoginPage
import com.example.petepath.pages.SignupPage

class MyAppNavigation {
}

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination ="login", builder = { //authViewModel: AuthViewModel
//        composable("login"){
//            LoginPage(modifier, navController)  // authViewModel
//        }
        composable("signup"){
            SignupPage(modifier, navController)
        }
        composable("home"){
            HomePage(modifier, navController)
        }
    })
}