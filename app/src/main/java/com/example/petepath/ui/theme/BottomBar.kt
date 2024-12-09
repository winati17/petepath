package com.example.petepath.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.R

enum class BottomBarScreen(val route: String) {
    Home("home"),
    History("route_history"),
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
            screen = BottomBarScreen.History,
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

@Composable
fun BottomBarIcon(
    screen: BottomBarScreen,
    currentScreen: BottomBarScreen,
    navController: NavController
) {
    val isActive = screen == currentScreen
    val iconRes = when (screen) {
        BottomBarScreen.Home -> if (isActive) R.drawable.vector_homepage else R.drawable.unactive_homepage
        BottomBarScreen.History -> if (isActive) R.drawable.vector_history else R.drawable.unactive_history
        BottomBarScreen.Report -> if (isActive) R.drawable.vector_report else R.drawable.unactive_report
        BottomBarScreen.Profile -> if (isActive) R.drawable.vector_profile else R.drawable.unactive_profile
    }

    Image(
        painter = painterResource(id = iconRes),
        contentDescription = screen.name,
        modifier = Modifier
            .size(24.dp)
            .clickable {
                // Hindari navigasi ke screen yang sama
                if (screen != currentScreen) {
                    navController.navigate(screen.route) {
                        // Konfigurasi navigasi agar tidak menumpuk screen
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
    )
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

