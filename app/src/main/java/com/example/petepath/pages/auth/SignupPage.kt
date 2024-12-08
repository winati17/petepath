package com.example.petepath.pages.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.UserViewModelFactory
import com.example.petepath.ui.theme.PetePathTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context,
    viewModel: UserViewModel
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val outlineColor = Color(0xFF007BFF)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.SemiBold)) {
                    append("Mari Bergabung \nBersama ")
                }
                withStyle(style = SpanStyle(color = outlineColor, fontWeight = FontWeight.SemiBold)) {
                    append("PetePath")
                }
            },
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,  // Transparent background
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,          // Use outlineColor here
                unfocusedBorderColor = outlineColor
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,  // Transparent background
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,          // Use outlineColor here
                unfocusedBorderColor = outlineColor
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(text = "Password")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,  // Transparent background
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,          // Use outlineColor here
                unfocusedBorderColor = outlineColor
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // Validasi input sebelum menyimpan
                if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    coroutineScope.launch {
                        // Cek apakah email atau username sudah digunakan
                        val existingUser = viewModel.getAllUsers().first().find {
                            it.email.equals(email, ignoreCase = true) || it.username.equals(username, ignoreCase = true)
                        }

                        if (existingUser != null) {
                            // Tampilkan pesan error jika email atau username sudah digunakan
                            Toast.makeText(context, "Username atau Email sudah digunakan", Toast.LENGTH_SHORT).show()
                        } else {
                            // Simpan data pengguna baru
                            viewModel.saveUserData(username, email, password)
                            // Setelah menyimpan, navigasi ke Login
                            Toast.makeText(context, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.Login.route) {
                                // Menghapus history agar user tidak kembali ke halaman signup
                                popUpTo(Screen.Home.route) { inclusive = false }
                            }
                        }
                    }
                } else {
                    // Tampilkan pesan error jika input tidak lengkap
                    Toast.makeText(context, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .width(275.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = outlineColor,
                contentColor = Color.White
            )
        ) {
            Text(text = "Daftar")
        }

    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSignupPage() {
//    PetePathTheme {
//        SignupPage(navController = rememberNavController(), context = LocalContext.current)
//    }
//}