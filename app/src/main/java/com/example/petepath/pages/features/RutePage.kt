package com.example.petepath.pages.features

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon

@Composable
fun RutePage(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentColor = Color(0xFF007BFF)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HomepageIcon(active = true, navController = navController)
                    HistoryIcon(navController = navController)
                    ReportIcon(navController = navController)
                    ProfileIcon(navController = navController)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Rute 01 | Sudiang",
                color = Color(0xFF007BFF),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // MapView()

            Spacer(modifier = Modifier.height(16.dp))

            routeList.forEach { route ->
                RouteItem(route = route)
            }
        }
    }
}

@Composable
fun RouteItem(route: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = Color(0xFF007BFF), shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = route,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

// Daftar rute yang akan ditampilkan
val routeList = listOf(
    "Jl. Manyikkoaya",
    "Jl. Arung Teko",
    "Jl. Pai",
    "Jl. Poros Pattene",
    "Jl. Poros Makassar-Maros",
    "Jl. Raya Puri Pattene",
    "Jl. Dakota",
    "Jl. Asrama Haji Sudiang",
    "Jl. Asoka"
)

@Preview(showBackground = true)
@Composable
fun PreviewRoutePageScreen() {
    RutePage(navController = rememberNavController())
}