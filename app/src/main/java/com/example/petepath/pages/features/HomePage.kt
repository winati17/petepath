package com.example.petepath.pages.features

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.Screen

@Composable
fun HomePage(userName: String, navController: NavController) {
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
                    .padding(innerPadding) // Menghindari konten tertutup oleh BottomAppBar
            ) {
                // Header
                item {
                    Text(
                        text = "Halo, $userName! \uD83D\uDC4B",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007BFF)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Recent Routes Section
                item {
                    Text(
                        text = "Terakhir Dikunjungi",
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
                        items(
                            listOf(
                                "Route 01 | Sudiang",
                                "Route 02 | Unhas",
                                "Route 03 | BTP",
                                "Route 04 | Pettarani"
                            )
                        ) { routeName ->
                            RecentRoute(routeName = routeName, navController = navController)
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
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // Jika jumlah route tidak genap, tambahkan Spacer
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
fun RecentRoute(routeName: String, navController: NavController) {
    Card(
        modifier = Modifier
            .width(184.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = routeName,
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
                    Log.d("NavController", "Button clicked. Attempting to navigate to ${Screen.Rute.route}.")
                    navController.navigate(Screen.Rute.route)
                },
                text = "Lihat Rute"
            )
        }
    }
}

@Composable
fun AllRoute(
    routeNumber: String,
    routeName: String,
    price: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(200.dp) // Sesuaikan tinggi sesuai kebutuhan
            .clickable {
                navController.navigate(Screen.Rute.route)
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Pete2Icon()
            Spacer(modifier = Modifier.height(8.dp))

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
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewHomePage() {
    HomePage(userName = "Cantik", navController = rememberNavController())
}
