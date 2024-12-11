package com.example.petepath.pages.features.homepage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.petepath.UserViewModel
import com.example.petepath.data.RouteRepository
import com.example.petepath.ui.theme.BottomBar
import com.example.petepath.ui.theme.BottomBarScreen

@Composable
fun HomePage(
    navController: NavController,
    viewModel: UserViewModel
)  {
    val currentUserEmail by viewModel.currentUserEmail.collectAsState()
    val userHistory by viewModel.userHistory.collectAsState()
    val users by viewModel.users.collectAsState(initial = emptyList())

    val displayName = users.find { it.email == currentUserEmail }?.username ?: "User"
    val allRoutes = RouteRepository.getAllRoutes()
    val routePairs = allRoutes.chunked(2)

    val currentScreen = BottomBarScreen.Home

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                navController = navController
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(innerPadding)
            ) {
                // Header
                item {
                    Text(
                        text = "Halo, $displayName! \uD83D\uDC4B",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007BFF)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (userHistory.isNotEmpty()){
                    // Recent Routes Section
                    item {
                        Text(
                            text = "Terakhir Dilihat",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Tampilkan maksimal 4 riwayat terbaru
                            items(userHistory.takeLast(4).reversed()) { historyItem ->
                                RecentRoute(
                                    ruteNumber = historyItem.routeNumber,
                                    ruteName = historyItem.routeName,
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                }

                // All Routes Section
                item {
                    Text(
                        text = "Pilih Rute Pete-Pete",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                items(routePairs) { pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AllRoute(
                            routeNumber = pair[0].id,
                            routeName = pair[0].name,
                            price = pair[0].price,
                            navController = navController,
                            viewModel = viewModel,
                            modifier = Modifier.weight(1f)
                        )
                        if (pair.size > 1) {
                            AllRoute(
                                routeNumber = pair[1].id,
                                routeName = pair[1].name,
                                price = pair[1].price,
                                navController = navController,
                                viewModel = viewModel,
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

//@Preview (showBackground = true)
//@Composable
//fun PreviewHomePage() {
//    PetePathTheme {
//        HomePage(navController = rememberNavController())
//    }
//}