package com.example.petepath.pages.features

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import com.example.petepath.AuthViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun HomePage(userName : String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White),
    ) {
        // Welcome Message
        Text(
            text = "Halo, $userName! \uD83D\uDC4B",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007BFF)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Section Header
        Text(
            text = "Terakhir Dikunjungi",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Recently Visited Routes
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            // Add your items
            items(listOf(
                "Route 01 | Sudiang",
                "Route 02 | Unhas",
                "Route 03 | BTP",
                "Route 04 | Pettarani"
            )) { routeName ->
                RecentRoute(routeName = routeName)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second Section Header
        Text(
            text = "Pilih Rute Pete-Pete",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Available Routes
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Create a 2-column grid
            modifier = Modifier
                .weight(1f) // Use weight to make it scrollable while leaving space for the bottom bar
                .padding(top = 4.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Add your routes as grid items
            items(
                listOf(
                    Triple("01", "Sudiang", "Rp2.500"),
                    Triple("02", "Unhas", "Rp3.000"),
                    Triple("03", "BTP", "Rp3.000"),
                    Triple("04", "Pettarani", "Rp3.500")
                )
            ) { route ->
                AllRoute(routeNumber = route.first, routeName = route.second, price = route.third)
            }
        }

        BottomAppBar(
            contentColor = Color(0xFF007BFF),
            containerColor = Color.White
        ) {
            HomepageIcon(active = true)
            Spacer(modifier = Modifier.weight(1f))
            HistoryIcon()
            Spacer(modifier = Modifier.weight(1f))
            ReportIcon()
            Spacer(modifier = Modifier.weight(1f))
            ProfileIcon()
        }
    }
}

@Composable
fun RecentRoute(routeName: String) {
    Card(
        modifier = Modifier
            .width(184.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                onClick = { /* TODO: Handle button click */ },
                text = "Lihat Rute"
            )
        }
    }
}

@Composable
fun AllRoute(routeNumber: String, routeName: String, price: String,
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() // Make the Column fill the entire card
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Center the content vertically
        ) {
            Pete2Icon() // Your icon composable
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

@Preview
@Composable
fun PreviewHomePage() {
    HomePage(userName = "Cantik")
}
