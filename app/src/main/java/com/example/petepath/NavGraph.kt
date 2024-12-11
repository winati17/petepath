package com.example.petepath

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.petepath.pages.auth.LoginPage
import com.example.petepath.pages.auth.SignupPage
import com.example.petepath.pages.features.homepage.HomePage
import com.example.petepath.pages.auth.Profile
import com.example.petepath.pages.features.reportpage.ReportHistoryPage
import com.example.petepath.pages.features.routepage.RoutePage
import com.example.petepath.pages.features.reportpage.ReportPage
import com.example.petepath.pages.features.activitypage.ActivityPage
import com.example.petepath.pages.auth.UserListPage

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: UserViewModel
){
    val currentUserEmail by viewModel.currentUserEmail.collectAsState()

    val startDestination = if (currentUserEmail != null) {
        Screen.Home.route
    } else {
        Screen.Login.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.Home.route){
            HomePage(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.Route.route,
            arguments = listOf(navArgument("ruteId") { type = NavType.StringType })
        ) { backStackEntry ->
            val ruteId = backStackEntry.arguments?.getString("ruteId") ?: "default"
            RoutePage(ruteId = ruteId, navController = navController)
        }
        composable(route = Screen.Activity.route){
            ActivityPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.ReportHistory.route){
            ReportHistoryPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Report.route){
            ReportPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Profile.route){
            Profile(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Login.route) {
            LoginPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.Signup.route) {
            SignupPage(navController = navController, viewModel = viewModel)
        }
        composable(route = Screen.UserList.route) {
            UserListPage(navController = navController, viewModel = viewModel)
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Route : Screen("rute/{ruteId}") {
        fun createRoute(ruteId: String) = "rute/$ruteId"
    }
    object Activity : Screen("activity")
    object Report : Screen("report")
    object ReportHistory : Screen("report_history")
    object Profile : Screen("profile")
    object Login: Screen("login")
    object Signup: Screen("signup")
    object UserList: Screen("user_list")
}