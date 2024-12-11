package com.example.petepath.pages.features.homepage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petepath.R
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.DataHistoryItem
import kotlinx.coroutines.launch


@Composable
fun AllRoute(
    routeNumber: String,
    routeName: String,
    price: String,
    navController: NavController,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = modifier
            .height(220.dp)
            .clickable {
                coroutineScope.launch {
                    val currentDate = java.text.SimpleDateFormat("dd MMMM yyyy, HH:mm", java.util.Locale.getDefault()).format(java.util.Date())

                    val historyItem = DataHistoryItem(
                        routeNumber = routeNumber,
                        routeName = routeName,
                        date = currentDate
                    )

                    viewModel.addHistoryItem(historyItem)

                    navController.navigate(Screen.Route.createRoute(routeNumber))
                }
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF007BFF)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.vector_pete2),
                contentDescription = "Pete-pete",
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = "Rute $routeNumber",
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