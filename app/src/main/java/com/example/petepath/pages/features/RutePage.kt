package com.example.petepath.pages.features

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon

@Composable
fun RutePage() {
    Log.d("NavController", "Rute Page displayed")
    Scaffold(
        bottomBar = {
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Rute 01 | Sudiang",
                color = Color.Blue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

//            MapView()

            Spacer(modifier = Modifier.height(16.dp))

            RouteList()
        }
    }
}


//@Composable
//fun MapView() {
//    // Placeholder for a map, you can use an Image or Google Maps API
//    Image(
//        painter = painterResource(id = R.drawable.placeholder_map), // Replace with your map resource
//        contentDescription = "Map",
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp)
//    )
//}

@Composable
fun RouteList() {
    val routes = listOf(
        "Jl. Manyikkoaya",
        "Jl. Arung Teko",
        "Jl. Pai",
        "Jl. Poros Pattene",
        "Jl. Poros Makassar-Maros",
        "Jl. Raya Puri Pattene",
        "Jl. Dakota",
        "Jl. Asrama Haji Sudiang",
        "Jl. Asoka"
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(routes.size) { index ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // Garis vertikal dan titik biru
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    // Garis biru vertikal
                    Divider(
                        modifier = Modifier
                            .width(2.dp)
                            .fillMaxHeight()
                            .padding(vertical = if (index == 0) 12.dp else 0.dp)
                            .align(Alignment.Center),
                        color = Color.Blue
                    )
                    // Titik biru
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(color = Color.Blue, shape = CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = routes[index],
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewRoutePageScreen() {
    RutePage()
}