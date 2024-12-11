package com.example.petepath.pages.auth

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.petepath.UserViewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.example.petepath.Screen
import com.example.petepath.ui.theme.BottomBar
import com.example.petepath.ui.theme.BottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListPage(
    navController: NavController,
    viewModel: UserViewModel
) {
    val users by viewModel.getAllUsers().collectAsState(initial = emptyList())
    val currentScreen = BottomBarScreen.Profile
    val contextLocal = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Daftar Akun",
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF007BFF)
                    )
                },
            )
        },
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                navController = navController
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(users) { user ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = "Username: ${user.username}", fontWeight = FontWeight.Bold)
                                Text(text = "Email: ${user.email}", color = Color.Gray)
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        viewModel.clearUser()
                        Toast.makeText(contextLocal, "Seluruh akun telah berhasil di hapus", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Profile.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Hapus Seluruh Akun")
                }
            }
        }
    )
}

