//package com.example.petepath.pages
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.List
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.tooling.preview.Preview
//
//class RoutePageActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            RoutePageScreen()
//        }
//    }
//}
//
//@Composable
//fun RoutePageScreen() {
//    Scaffold(
//        bottomBar = { BottomNavigationBar() }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Rute 01 | Sudiang",
//                color = Color.Blue,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            MapView()
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            RouteList()
//        }
//    }
//}
//
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
//
//@Composable
//fun RouteList() {
//    val routes = listOf(
//        "Jl. Manyikkoaya",
//        "Jl. Arung Teko",
//        "Jl. Pai",
//        "Jl. Poros Pattene",
//        "Jl. Poros Makassar-Maros",
//        "Jl. Raya Puri Pattene",
//        "Jl. Dakota",
//        "Jl. Asrama Haji Sudiang",
//        "Jl. Asoka"
//    )
//
//    LazyColumn {
//        items(routes.size) { index ->
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(vertical = 8.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.List,
//                    contentDescription = "Route Point",
//                    tint = Color.Blue,
//                    modifier = Modifier.size(16.dp)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(text = routes[index], fontSize = 18.sp)
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewRoutePageScreen() {
//    RoutePageScreen()
//}