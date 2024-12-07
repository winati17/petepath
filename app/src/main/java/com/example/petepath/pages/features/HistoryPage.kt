package com.example.petepath.pages.features

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.R
import com.example.petepath.UserViewModel
import com.example.petepath.data.UserViewModelFactory
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.PetePathTheme
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon

@Composable
fun HistoryPage(navController: NavController,
                context: Context = LocalContext.current) {
    val mainColor = Color(0xFF007BFF)
    val petepete: Painter = painterResource(id = R.drawable.vector_pete2)
    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )
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
                HistoryIcon(active = true, navController = navController)
                ReportIcon(navController = navController)
                ProfileIcon(navController = navController)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 45.dp),
        ) {
            // Header Text
            Text(
                text = "Riwayat Kegiatan",
                color = mainColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(45.dp))

            // Menampilkan data userHistory jika ada
            if (userHistory.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(userHistory) { historyItem ->
                        HistoryItem(
                            painter = petepete,
                            routeNumber = historyItem.routeNumber,
                            routeName = historyItem.routeName,
                            date = historyItem.date,
                            mainColor = mainColor
                        )
                    }
                }
            } else {
                // Menampilkan pesan jika tidak ada riwayat
                Text(
                    text = "No history available",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
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
            .width(300.dp)
            .height(120.dp)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painter,
                contentDescription = "Pete-Pete",
                modifier = Modifier.size(90.dp)
            )
            Column (
                verticalArrangement = Arrangement.Center
            ){
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


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HistoryActivityPreview(){
    PetePathTheme {
        HistoryPage(navController = rememberNavController())
    }
}