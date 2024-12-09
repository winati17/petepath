package com.example.petepath.pages.features

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.UserViewModel
import com.example.petepath.data.DataHistoryItem
import com.example.petepath.data.Route
import com.example.petepath.data.UserPreferencesRepository
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.PetePathTheme
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon

@Composable
fun RutePage(
    ruteId: String,
    navController: NavController,
    context: Context,
    viewModel: UserViewModel
){
    // Mengambil data rute menggunakan RouteRepository
    val route = RouteRepository.getRouteById(ruteId)
    val routeName = route?.name ?: "Unknown"
    val routesForRute = getRoutesByRuteId(ruteId)

    // Mengambil email pengguna saat ini
    val currentUserEmail by viewModel.currentUserEmail.collectAsState()

    if (routeName == "Unknown") {
        // Tampilkan pesan error jika rute tidak ditemukan
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
                HomepageIcon(active = false, navController = navController)
                HistoryIcon(active = false, navController = navController)
                ReportIcon(active = false, navController = navController)
                ProfileIcon(active = false, navController = navController)
            }
        }
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

            // Menampilkan daftar rute
            routesForRute.forEach { route ->
                RouteItem(route = route)
            }
        }
    }
}

object RouteRepository {
    private val routes = listOf(
        Route("02", "Unhas-Cendrawasih", "Rp5.000"),
        Route("05", "Unhas-Veteran", "Rp5.000"),
        Route("07", "Unhas-Pettarani", "Rp5.000"),
//        Route("04", "Pettarani", "Rp3.500")
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
        "02" -> listOf(
            "Terminal Tamalate",
            "Malengkeri",
            "Dg. Tata",
            "Abd. Kadir",
            "Dangko",
            "Cendrawasih",
            "Arif Rate",
            "Botolempangan",
            "Kartini",
            "Bawakaraeng",
            "Urip Sumoharjo",
            "Perintis Kemerdekaan",
            "Kampus Unhas"
        )
        "02" -> listOf(
            "Jl. Unhas 1",
            "Jl. Unhas 2",
            "Jl. Unhas 3",
            // Tambahkan rute lainnya untuk Unhas
        )
        "03" -> listOf(
            "Jl. BTP 1",
            "Jl. BTP 2",
            "Jl. BTP 3",
            // Tambahkan rute lainnya untuk BTP
        )
        "04" -> listOf(
            "Jl. Pettarani 1",
            "Jl. Pettarani 2",
            "Jl. Pettarani 3",
            // Tambahkan rute lainnya untuk Pettarani
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
