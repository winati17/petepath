package com.example.petepath.pages.features.routepage

import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import com.example.petepath.data.RouteRepository
import com.example.petepath.data.getRoutesByRuteId
import com.example.petepath.ui.theme.BottomBar
import com.example.petepath.ui.theme.BottomBarScreen

@Composable
fun RoutePage(
    ruteId: String,
    navController: NavController,
){
    val route = RouteRepository.getRouteById(ruteId)
    val routeName = route?.name ?: "Unknown"
    val routesForRute = getRoutesByRuteId(ruteId)
    val currentScreen = BottomBarScreen.Home

    if (routeName == "Unknown") {
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
            BottomBar(
                currentScreen = currentScreen,
                navController = navController
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp) // Tambahkan padding sesuai kebutuhan
        ) {
            Text(
                text = "Rute $ruteId | $routeName",
                color = Color(0xFF007BFF),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder untuk MapView atau elemen UI lainnya
            // Contoh:
            // MapView()

            Spacer(modifier = Modifier.height(16.dp))

            routesForRute.forEach { route ->
                RouteItem(route = route)
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewRoutePageScreen() {
//    // Mock NavController untuk preview
//    val navController = rememberNavController()
//
//    // Mock UserViewModel untuk preview
//    val context = LocalContext.current
//    val repository = UserPreferencesRepository(context = context)
//    val viewModel = remember {
//        UserViewModel(repository = repository).apply {
//            // Tambahkan pengguna palsu untuk preview
//            saveUserData(username = "TestUser", email = "test@example.com", password = "password")
//            _currentUserEmail.value = "test@example.com"
//        }
//    }
//
//    // Preview RutePage dengan ruteId "01"
//    RutePage(
//        ruteId = "01",
//        navController = navController,
//        context = context,
//        viewModel = viewModel
//    )
//}
