package com.example.petepath

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.petepath.pages.auth.LoginPage
import com.example.petepath.pages.auth.SignupPage
import com.example.petepath.pages.features.HomePage
import com.example.petepath.pages.auth.Profile
import com.example.petepath.pages.features.ReportHistoryPage
import com.example.petepath.pages.features.RutePage
import com.example.petepath.pages.features.ReportPage
import com.example.petepath.pages.features.HistoryPage
import com.example.petepath.pages.features.UserListPage

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: UserViewModel,
    context: Context
){
    NavHost(
        navController= navController,
        startDestination= Screen.Login.route
    ){
        composable(route = Screen.Home.route){
            HomePage(navController = navController, context= context, viewModel = viewModel)
        }
        composable(
            route = Screen.Rute.route,
            arguments = listOf(navArgument("ruteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ruteId = backStackEntry.arguments?.getString("ruteId") ?: "default"
            RutePage(ruteId = ruteId, navController = navController, context = context, viewModel = viewModel)
        }
        composable(route = Screen.History.route){
            HistoryPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.ReportHistory.route){
            ReportHistoryPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Report.route){
            ReportPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Profile.route){
            Profile(navController = navController, context = context, viewModel = viewModel)
        }
        composable(route = Screen.Login.route) {
            LoginPage(navController = navController, context = context, viewModel = viewModel)
        }
        composable(route = Screen.Signup.route) {
            SignupPage(navController = navController, context = context, viewModel = viewModel)
        }
        composable(route = Screen.UserList.route) {
            UserListPage(navController = navController, context = context, viewModel = viewModel)
        }
    }
}

sealed class Screen(val route: String) {
    object Home: Screen(route= "home_page")
    object Rute : Screen("rute/{ruteId}") {
        fun createRoute(ruteId: String) = "rute/$ruteId"
    }
    object History: Screen(route= "history_page")
    object Report: Screen(route= "report_page")
    object ReportHistory: Screen(route= "reporthistory_page")
    object Profile: Screen(route= "profile")
    object Login: Screen(route= "login")
    object Signup: Screen(route= "signup")
    object UserList: Screen(route= "user_list")
}