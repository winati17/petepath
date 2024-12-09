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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import kotlinx.coroutines.launch

@Composable
fun SignupPage(
    navController: NavController,
    context: Context,
    viewModel: UserViewModel
) {
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }


    val outlineColor = Color(0xFF007BFF)
    val viewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
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
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = "Nama") },
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
            label = { Text(text = "Password") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password"
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,
                unfocusedBorderColor = outlineColor
            )
        )


        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // Validasi input sebelum menyimpan
                if (nama.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    coroutineScope.launch {
                        viewModel.saveUserData(nama, email, password)
                        // Setelah menyimpan, navigasi ke Login
                        Toast.makeText(context, "Data berhasil ditambah", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Login.route) {
                            // Menghapus history agar user tidak kembali ke halaman signup
                            popUpTo(Screen.Home.route) { inclusive = false }
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