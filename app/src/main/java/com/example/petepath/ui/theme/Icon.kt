package com.example.petepath.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petepath.R

@Composable
fun BottomBarIcon(
    screen: BottomBarScreen,
    currentScreen: BottomBarScreen,
    navController: NavController
) {
    val isActive = screen == currentScreen
    val iconRes = when (screen) {
        BottomBarScreen.Home -> if (isActive) R.drawable.vector_homepage else R.drawable.unactive_homepage
        BottomBarScreen.Activity -> if (isActive) R.drawable.vector_activity else R.drawable.unactive_activity
        BottomBarScreen.Report -> if (isActive) R.drawable.vector_report else R.drawable.unactive_report
        BottomBarScreen.Profile -> if (isActive) R.drawable.vector_profile else R.drawable.unactive_profile
    }

    Image(
        painter = painterResource(id = iconRes),
        contentDescription = screen.name,
        modifier = Modifier
            .size(24.dp)
            .clickable {
                navController.navigate(screen.route)
            }
    )
}