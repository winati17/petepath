package com.example.petepath.pages.features

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.R
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.UserViewModelFactory
import com.example.petepath.ui.theme.PetePathTheme

@Composable
fun HistoryPage(navController: NavController, context: Context = LocalContext.current, viewModel: UserViewModel) {
    val mainColor = Color(0xFF007BFF)
    val petepete: Painter = painterResource(id = R.drawable.vector_pete2)
    val userHistory by viewModel.userHistory.collectAsState()

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
        if (userHistory.isNotEmpty()) {
            // Tampilan ketika ada riwayat
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 16.dp)
            ) {
                // Header Text
                Text(
                    text = "Riwayat Kegiatan",
                    color = mainColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Menampilkan data userHistory jika ada
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(userHistory.reversed()) { historyItem ->
                        HistoryItem(
                            painter = petepete,
                            routeNumber = historyItem.routeNumber,
                            routeName = historyItem.routeName,
                            date = historyItem.date,
                            mainColor = mainColor
                        )
                    }
                }
            }
        } else {
            // Tampilan ketika tidak ada riwayat
            AddActivityView(navController = navController, paddingValues = paddingValues)
        }
    }
}
@Composable
fun HistoryItem(
    painter: Painter,
    routeNumber: String,
    routeName: String,
    date: String,
    mainColor: Color
) {
    Box(
        modifier = Modifier
            .border(3.dp, color = mainColor, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = "Pete-Pete",
                modifier = Modifier.size(90.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Rute $routeNumber | $routeName",
                    color = mainColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = date,
                    fontSize = 10.sp,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
fun AddActivityView(navController: NavController, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Riwayat Aktivitas",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007BFF)
            ),
            modifier = Modifier
                .padding(bottom = 5.dp, top = 30.dp)
                .align(Alignment.Start)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.leaf),
                contentDescription = "Vector Image",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Tidak ada aktivitas terbaru.",
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
                    navController.navigate(Screen.Home.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                shape = RoundedCornerShape(11.dp),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "+ Tambah Aktivitas",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewActivityHistory(){
//    PetePathTheme {
//        HistoryPage(navController = rememberNavController())
//    }
//}
