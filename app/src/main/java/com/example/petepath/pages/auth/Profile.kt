package com.example.petepath.pages.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.UserViewModelFactory
import com.example.petepath.ui.theme.HomepageIcon
import com.example.petepath.ui.theme.HistoryIcon
import com.example.petepath.ui.theme.PetePathTheme
import com.example.petepath.ui.theme.ProfileIcon
import com.example.petepath.ui.theme.ReportIcon
import kotlinx.coroutines.launch

@Composable
fun Profile(navController: NavController, context: Context, viewModel: UserViewModel) {
    val mainColor = Color(0xFF007BFF)
    val currentUserEmail by viewModel.currentUserEmail.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val users by viewModel.users.collectAsState(initial = emptyList())

    val displayName = users.find { it.email == currentUserEmail }?.username ?: "Unknown"
    val email = currentUserEmail ?: "Unknown"

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
                ReportIcon(navController = navController)
                ProfileIcon(active = true, navController = navController)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Profil",
                color = mainColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 45.dp)
            )

            Spacer(modifier = Modifier.height(45.dp))

            // Box dengan informasi profil
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF0EFEF))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Avatar Box
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(80.dp)
                                .background(Color(0xFFD8D8D8))
                        )

                        Column(
                            modifier = Modifier.padding(start = 24.dp, end = 16.dp)
                        ) {
                            Text(
                                text = displayName,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = email,
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                viewModel.logout()
                                Toast.makeText(context, "Logout berhasil", Toast.LENGTH_SHORT).show()
                                navController.navigate(Screen.Login.route) {
                                    popUpTo(Screen.Profile.route) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier.width(230.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = mainColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Logout")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF0EFEF))
                    .padding(16.dp)
            ){
                Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                    Button(
                        onClick = {
                            navController.navigate(Screen.UserList.route)
                        },
                        modifier = Modifier
                            .width(230.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Lihat Daftar Akun")
                    }

                    Button(
                        onClick = {
                            viewModel.clearHistory()
                        },
                        modifier = Modifier
                            .width(230.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Hapus History Rute")
                    }
                }
            }
        }
    }
}


//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun ProfilePreview(){
//    PetePathTheme {
//        Profile(navController = rememberNavController(), context = LocalContext.current)
//    }
//}