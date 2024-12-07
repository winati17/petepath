package com.example.petepath.pages.features

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.R
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.ReportItem
import com.example.petepath.data.UserPreferencesRepository
import com.example.petepath.ui.theme.PetePathTheme

@Composable
fun ReportHistoryPage(navController: NavController, viewModel: UserViewModel) {

    val userReports by viewModel.userReports.collectAsState()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomepageIcon(navController = navController)
                HistoryIcon(navController = navController)
                ReportIcon(active = true, navController = navController)
                ProfileIcon(navController = navController)
            }
        }
    ) { paddingValues ->
        if (userReports.isEmpty()) {
            // Tampilan ketika tidak ada laporan
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Riwayat Laporan",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007BFF)
                    ),
                    modifier = Modifier
                        .padding(bottom = 5.dp, top = 30.dp)
                        .align(Alignment.Start)
                )
                Image(
                    painter = painterResource(id = R.drawable.leaf),
                    contentDescription = "Vector Image",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "Tidak ada laporan terbaru.",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    ),
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(Screen.Report.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                    shape = RoundedCornerShape(11.dp),
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(
                        text = "+ Tambah Laporan",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            // Tampilan ketika ada laporan
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Riwayat Laporan",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF007BFF)
                    ),
                    modifier = Modifier
                        .padding(bottom = 5.dp, top = 30.dp)
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(userReports) { report ->
                        ReportItemCard(report = report)
                    }
                }
            }
        }
    }
}

@Composable
fun ReportItemCard(report: ReportItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Rute ${report.routeNumber} | ${report.routeName}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007BFF)
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Kategori: ${report.violationCategory}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Deskripsi: ${report.description}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Plat Kendaraan: ${report.vehiclePlate}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Tanggal: ${report.date}",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewReportHistory(){
//    // Mock NavController dan ViewModel untuk preview
//    val navController = rememberNavController()
//    val context = LocalContext.current
//    val repository = UserPreferencesRepository(context = context)
//    val viewModel = remember {
//        UserViewModel(repository = repository).apply {
//            // Tambahkan pengguna palsu untuk preview
//            saveUserData(username = "TestUser", email = "test@example.com", password = "password")
//            _currentUserEmail.value = "test@example.com"
//            // Tambahkan laporan palsu
//            addReport(ReportItem(
//                routeNumber = "01",
//                routeName = "Sudiang",
//                violationCategory = "Mengabaikan rambu lalu lintas",
//                description = "Mengabaikan rambu berhenti di lampu merah.",
//                vehiclePlate = "B 1234 CD",
//                date = "01 Januari 2024, 10:00"
//            ))
//        }
//    }
//
//    PetePathTheme {
//        ReportHistoryPage(navController = navController, viewModel = viewModel)
//    }
//}