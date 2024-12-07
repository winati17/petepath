package com.example.petepath.pages.features

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.R
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon

@Composable
fun HistoryPage(navController: NavController) {
    val mainColor = Color(0xFF007BFF)
    val petepete: Painter = painterResource(id = R.drawable.vector_pete2)

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
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 45.dp)
            )

            Spacer(modifier = Modifier.height(45.dp))

            // Box Content
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HistoryItem(petepete, "Rute 01 | Sudiang", "22 Oktober 2024, 09:09", mainColor)
                Spacer(modifier = Modifier.height(16.dp))
                HistoryItem(petepete, "Rute 02 | Unhas", "5 November 2024, 09:09", mainColor)
            }
        }
    }
}

@Composable
fun HistoryItem(painter: Painter, route: String, date: String, mainColor: Color) {
    Box(
        modifier = Modifier
            .border(3.dp, color = mainColor, shape = RoundedCornerShape(16.dp))
            .width(300.dp)
            .height(120.dp)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Pete-Pete",
                    modifier = Modifier.size(90.dp).padding(start = 16.dp),
                )
                Column {
                    Text(
                        text = route,
                        color = mainColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        text = "Rp 2.500,-",
                        color = mainColor
                    )
                }
            }
            Text(
                text = date,
                fontSize = 10.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.End).padding(end = 16.dp)
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HistoryActivityPreview(){
    HistoryPage(navController = rememberNavController())
}