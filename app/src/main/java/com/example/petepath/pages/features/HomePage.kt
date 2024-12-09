package com.example.petepath.pages.features

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.PrimaryButton
import com.example.petepath.ui.theme.Pete2Icon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.UserViewModelFactory
import kotlinx.coroutines.launch
import com.example.petepath.data.DataHistoryItem
import com.example.petepath.ui.theme.PetePathTheme

@Composable
fun HomePage(
    navController: NavController,
    context: Context = LocalContext.current,
    viewModel: UserViewModel
)  {
    val currentUserEmail by viewModel.currentUserEmail.collectAsState()
    val userHistory by viewModel.userHistory.collectAsState()
    val users by viewModel.users.collectAsState(initial = emptyList())

    val displayName = users.find { it.email == currentUserEmail }?.username ?: "User"

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
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(innerPadding) // Avoid content being overlapped by BottomAppBar
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

                // Grid of All Routes
                items(chunkedAllRoutes()) { rowRoutes ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (route in rowRoutes) {
                            AllRoute(
                                routeNumber = route.first,
                                routeName = route.second,
                                price = route.third,
                                navController = navController,
                                viewModel = viewModel,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // If the number of routes is odd, add a Spacer
                        if (rowRoutes.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

// Fungsi untuk mengelompokkan daftar route menjadi pasangan (chunked)
private fun chunkedAllRoutes(): List<List<Triple<String, String, String>>> {
    val allRoutes = listOf(
        Triple("01", "Sudiang", "Rp2.500"),
        Triple("02", "Unhas", "Rp3.000"),
        Triple("03", "BTP", "Rp3.000"),
        Triple("04", "Pettarani", "Rp3.500")
    )
    return allRoutes.chunked(2)
}

@Composable
fun RecentRoute(
    ruteNumber: String,
    ruteName: String,
    navController: NavController,
    viewModel: UserViewModel
) {
    val mainColor = Color(0xFF007BFF)
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .width(184.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Gunakan Box untuk memusatkan konten secara vertikal dan horizontal
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Rute $ruteNumber | $ruteName",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                PrimaryButton(
                    onClick = {
                        coroutineScope.launch {
                            // Dapatkan tanggal dan waktu saat ini
                            val currentDate = java.text.SimpleDateFormat("dd MMMM yyyy, HH:mm", java.util.Locale.getDefault()).format(java.util.Date())

                            // Buat objek DataHistoryItem
                            val historyItem = DataHistoryItem(
                                routeNumber = ruteNumber,
                                routeName = ruteName,
                                date = currentDate
                            )

                            // Tambahkan ke riwayat
                            viewModel.addHistoryItem(historyItem)

                            // Navigasi ke halaman Rute
                            navController.navigate(Screen.Rute.createRoute(ruteNumber))
                        }
                    },
                    text = "Lihat Rute"
                )
            }
        }
    }
}


@Composable
fun AllRoute(
    routeNumber: String,
    routeName: String,
    price: String,
    navController: NavController,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    val mainColor = Color(0xFF007BFF)
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .height(220.dp)
            .clickable {
                coroutineScope.launch {
                    // Dapatkan tanggal dan waktu saat ini
                    val currentDate = java.text.SimpleDateFormat("dd MMMM yyyy, HH:mm", java.util.Locale.getDefault()).format(java.util.Date())

                    // Buat objek DataHistoryItem
                    val historyItem = DataHistoryItem(
                        routeNumber = routeNumber,
                        routeName = routeName,
                        date = currentDate
                    )

                    // Tambahkan ke riwayat
                    viewModel.addHistoryItem(historyItem)

                    // Navigasi ke halaman Rute
                    navController.navigate(Screen.Rute.createRoute(routeNumber))
                }
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Pete2Icon()
            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "Route $routeNumber",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007BFF),
                textAlign = TextAlign.Center
            )

            Text(
                text = routeName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Text(
                text = price,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

//@Preview (showBackground = true)
//@Composable
//fun PreviewHomePage() {
//    PetePathTheme {
//        HomePage(navController = rememberNavController())
//    }
//}