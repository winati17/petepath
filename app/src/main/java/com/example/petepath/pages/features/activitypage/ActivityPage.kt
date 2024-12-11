package com.example.petepath.pages.features.activitypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petepath.R
import com.example.petepath.UserViewModel
import com.example.petepath.ui.theme.BottomBar
import com.example.petepath.ui.theme.BottomBarScreen

@Composable
fun ActivityPage(
    navController: NavController,
    viewModel: UserViewModel
) {
    val mainColor = Color(0xFF007BFF)
    val petepete: Painter = painterResource(id = R.drawable.vector_pete2)
    val userHistory by viewModel.userHistory.collectAsState()
    val currentScreen = BottomBarScreen.Activity

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                navController = navController
            )
        },
    ) { paddingValues ->
        if (userHistory.isNotEmpty()) {
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
                    items(userHistory.reversed()) { activityItem ->
                        ActivityItem(
                            painter = petepete,
                            routeNumber = activityItem.routeNumber,
                            routeName = activityItem.routeName,
                            date = activityItem.date,
                            mainColor = mainColor
                        )
                    }
                }
            }
        } else {
            NoActivity(navController = navController, paddingValues = paddingValues)
        }
    }
}




//@Preview(showBackground = true)
//@Composable
//fun PreviewActivityHistory(){
//    PetePathTheme {
//        HistoryRoutePage(navController = rememberNavController(), paddingValues =padding)
//    }
//}
