package com.example.petepath.pages.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.ui.theme.PasswordTextField
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import android.util.Patterns

@Composable
fun SignupPage(
    navController: NavController,
    viewModel: UserViewModel
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context= LocalContext.current
    val outlineColor = Color(0xFF007BFF)
    val coroutineScope = rememberCoroutineScope()

    var emailError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
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
            onValueChange = {
                username = it
                if (usernameError) {
                    usernameError = false
                }
            },
            shape = RoundedCornerShape(8.dp),
            label = { Text(text = "Username") },
            singleLine = true,
            isError = usernameError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,
                unfocusedBorderColor = outlineColor
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                if (emailError) {
                    emailError = false
                } },
            shape = RoundedCornerShape(8.dp),
            label = { Text(text = "Email") },
            singleLine = true,
            isError = emailError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,
                unfocusedBorderColor = outlineColor
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        PasswordTextField(
            password = password,
            onPasswordChange = { password = it },
            mainColor = outlineColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    // Validasi format email
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = true
                        Toast.makeText(context, "Format email tidak valid", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    coroutineScope.launch {
                        // Cek apakah email atau username sudah digunakan
                        val existingUser = viewModel.getAllUsers().first().find {
                            it.email.equals(email, ignoreCase = true) || it.username.equals(username, ignoreCase = true)
                        }

                        if (existingUser != null) {
                            emailError = true
                            usernameError = true
                            Toast.makeText(context, "Username atau Email sudah digunakan", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.saveUserData(username, email, password)
                            Toast.makeText(context, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
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