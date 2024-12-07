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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.petepath.ui.theme.PetePathTheme

@Composable
fun ReportHistoryPage(navController: NavController) {
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
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportHistory(){
    PetePathTheme {
        ReportHistoryPage(navController = rememberNavController())
    }
}
