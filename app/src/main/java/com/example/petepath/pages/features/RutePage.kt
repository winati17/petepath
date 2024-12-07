package com.example.petepath.pages.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import com.example.petepath.data.RuteViewModel
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon

data class Route(
    val id: String,
    val name: String,
    val price: String
)

@Composable
fun RutePage(navController: NavController, viewModel: RuteViewModel) {
    val routeName = viewModel.routeName
    val routesForRute = viewModel.routesForRute

    if (routeName == "Unknown") {
        // Display an error message or navigate back
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rute tidak ditemukan.",
                color = Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Kembali")
            }
        }
        return
    }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomepageIcon(active = true, navController = navController)
                HistoryIcon(navController = navController)
                ReportIcon(navController = navController)
                ProfileIcon(navController = navController)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp) // Add additional padding if needed
        ) {
            Text(
                text = "Rute ${viewModel.ruteId} | $routeName",
                color = Color(0xFF007BFF),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder for MapView or other UI elements
            // MapView()

            Spacer(modifier = Modifier.height(16.dp))

            // Display the list of routes
            routesForRute.forEach { route ->
                RouteItem(route = route)
            }
        }
    }
}

object RouteRepository {
    private val routes = listOf(
        Route("01", "Sudiang", "Rp2.500"),
        Route("02", "Unhas", "Rp3.000"),
        Route("03", "BTP", "Rp3.000"),
        Route("04", "Pettarani", "Rp3.500")
    )

    fun getRouteById(ruteId: String): Route? {
        return routes.find { it.id == ruteId }
    }

    fun getAllRoutes(): List<Route> = routes
}

fun getRouteNameById(ruteId: String): String {
    return RouteRepository.getRouteById(ruteId)?.name ?: "Unknown"
}

fun getRoutesByRuteId(ruteId: String): List<String> {
    return when (ruteId) {
        "01" -> listOf(
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
        "02" -> listOf(
            "Jl. Unhas 1",
            "Jl. Unhas 2",
            "Jl. Unhas 3",
            // Add more routes for Unhas
        )
        "03" -> listOf(
            "Jl. BTP 1",
            "Jl. BTP 2",
            "Jl. BTP 3",
            // Add more routes for BTP
        )
        "04" -> listOf(
            "Jl. Pettarani 1",
            "Jl. Pettarani 2",
            "Jl. Pettarani 3",
            // Add more routes for Pettarani
        )
        else -> listOf("No routes available")
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

@Preview(showBackground = true)
@Composable
fun PreviewRoutePageScreen() {
    val fakeViewModel = RuteViewModel("01")
    RutePage(navController = rememberNavController(), viewModel = fakeViewModel)
}