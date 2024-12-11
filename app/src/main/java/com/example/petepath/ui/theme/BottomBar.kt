package com.example.petepath.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

enum class BottomBarScreen(val route: String) {
    Home("home"),
    Activity("activity"),
    Report("report_history"),
    Profile("profile")
}

@Composable
fun BottomBar(
    currentScreen: BottomBarScreen,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarIcon(
            screen = BottomBarScreen.Home,
            currentScreen = currentScreen,
            navController = navController
        )
        BottomBarIcon(
            screen = BottomBarScreen.Activity,
            currentScreen = currentScreen,
            navController = navController
        )
        BottomBarIcon(
            screen = BottomBarScreen.Report,
            currentScreen = currentScreen,
            navController = navController
        )
        BottomBarIcon(
            screen = BottomBarScreen.Profile,
            currentScreen = currentScreen,
            navController = navController
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    val navController = rememberNavController()
    BottomBar(
        currentScreen = BottomBarScreen.Home,
        navController = navController
    )
}

