
package com.example.petepath.pages.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.Screen

@Composable
fun LoginPage(navController: NavController){
    val mainColor = Color(0xFF007BFF)

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(text = "Halo, Selamat Datang! 👋", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(text = "Email")
            },
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

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {navController.navigate(Screen.Home.route)},
            colors = ButtonDefaults.buttonColors(
                containerColor = mainColor,
                contentColor = Color.White
            ),
            modifier = Modifier.width(275.dp).height(50.dp).
            border(width = 10.dp, color = mainColor) // Atur ketebalan dan warna border
        ) {
            Text(text = "Masuk")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Text(text = "Belum punya akun? ")
            Text(text = "Daftar sekarang!", modifier = Modifier.clickable { navController.navigate(Screen.Signup.route) }, color = mainColor)
        }
    }
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    mainColor: Color = Color(0xFF007BFF)
) {
    var showPassword by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = "Password") },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (showPassword)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(imageVector = image, contentDescription = if (showPassword) "Hide password" else "Show password")
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedBorderColor = mainColor,
            unfocusedBorderColor = mainColor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview(){
    LoginPage(navController = rememberNavController())
}

