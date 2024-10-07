package com.example.petepath.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
//import com.example.petepath.AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.petepath.ui.theme.Pete2Icon

@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        // Welcome Message
        Text(
            text = "Halo, Cantik!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007BFF)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Section Header
        Text(
            text = "Terakhir Dikunjungi",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Recently Visited Routes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RouteCard(routeName = "Route 01 | Sudiang", price = "Rp2.500", buttonText = "Lihat Rute")
//            RouteOutlinedCard(routeName = "Route 02 | Unhas", price = "Rp3.000", buttonText = "Lihat Rute")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second Section Header
        Text(
            text = "Pilih Rute Pete-Pete",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Available Routes
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RouteCard(routeName = "Route 01 Sudiang", price = "Rp2.500", buttonText = "")
//                RouteOutlinedCard(routeName = "Route 02 Unhas", price = "Rp3.000", buttonText = "")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RouteCard(routeName = "Route 03 BTP", price = "Rp3.000", buttonText = "")
//                RouteOutlinedCard(routeName = "Route 04 Pettarani", price = "Rp3.500", buttonText = "")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation (Placeholders)
//        BottomNavigationBar()
    }
}

@Composable
fun RouteCard(routeName: String, price: String, buttonText: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Pete2Icon()
            Spacer(modifier = Modifier.height(8.dp))

            // Route Name
            Text(
                text = routeName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007BFF)
            )

            // Price
            Text(
                text = price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Button (if provided)
            if (buttonText.isNotEmpty()) {
                Button(
                    onClick = { /* TODO: Handle button click */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF))
                ) {
                    Text(text = buttonText, color = Color.White)
                }
            }
        }
    }
}
//
//@Composable
//fun RouteOutlinedCard(routeName: String, price: String, buttonText: String) {
//    Card(
//        modifier = Modifier
//            .width(160.dp)
//            .padding(8.dp),
//        shape = RoundedCornerShape(8.dp),
//        border = BorderStroke(2.dp, Color(0xFF007BFF)),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Route Icon
//            Icon(
//                painter = painterResource(id = R.drawable.ic_truck), // Replace with actual resource
//                contentDescription = "Route Icon",
//                tint = Color(0xFF007BFF),
//                modifier = Modifier.size(48.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Route Name
//            Text(
//                text = routeName,
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF007BFF)
//            )
//
//            // Price
//            Text(
//                text = price,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Normal,
//                color = Color.Gray
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Outlined Button (if provided)
//            if (buttonText.isNotEmpty()) {
//                OutlinedButton(
//                    onClick = { /* TODO: Handle button click */ },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF007BFF)),
//                    border = BorderStroke(2.dp, Color(0xFF007BFF))
//                ) {
//                    Text(text = buttonText)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BottomNavigationBar() {
//    BottomAppBar(
//        contentColor = Color(0xFF007BFF),
//        containerColor = Color.White
//    ) {
//        // Navigation icons as placeholders
//        Icon(
//            painter = painterResource(id = R.drawable.ic_home), // Replace with actual resource
//            contentDescription = "Home",
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Icon(
//            painter = painterResource(id = R.drawable.ic_message), // Replace with actual resource
//            contentDescription = "Messages",
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Icon(
//            painter = painterResource(id = R.drawable.ic_user), // Replace with actual resource
//            contentDescription = "Profile",
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}

@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}
