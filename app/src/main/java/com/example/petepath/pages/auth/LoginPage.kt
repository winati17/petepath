
package com.example.petepath.pages.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.ui.theme.PasswordTextField
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: UserViewModel
) {
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val contextLocal = LocalContext.current
    val mainColor = Color(0xFF007BFF)

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(text = "Selamat Datang \ndi Petepath! 👋",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = usernameOrEmail,
            onValueChange = { usernameOrEmail = it },
            shape = RoundedCornerShape(8.dp),
            label = { Text(text = "Username atau Email") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = mainColor,
                unfocusedBorderColor = mainColor
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        PasswordTextField(
            password = password,
            onPasswordChange = { password = it },
            mainColor = mainColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    val isSuccess = viewModel.login(usernameOrEmail, password)
                    if (isSuccess) {
                        Toast.makeText(contextLocal, "Login berhasil", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        Toast.makeText(contextLocal, "Username/Email atau password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.width(280.dp).height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = mainColor,
                contentColor = Color.White
            ),
        ) {
            Text(text = "Masuk")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Text(text = "Belum punya akun? ")
            Text(
                text = "Daftar sekarang!",
                modifier = Modifier.clickable { navController.navigate(Screen.Signup.route) },
                color = mainColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginPagePreview(){
//    PetePathTheme {
//        LoginPage(navController = rememberNavController())
//    }
//}

