package com.example.petepath.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import com.example.petepath.AuthViewModel


@Composable
fun SignupPage(modifier: Modifier = Modifier, navController: NavController? = null) {
    var nama by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val outlineColor = Color(0xFF007BFF)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold)) {
                    append("Mari Bergabung \nBersama ")
                }
                withStyle(style = SpanStyle(
                    color = Color(0xFF007BFF),
                    fontWeight = FontWeight.SemiBold)) {
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
            label = {
                Text(text = "Nama")
            },
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
            label = {
                Text(text = "Email")
            },
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
            onClick = { /* Handle click */ },
            modifier = Modifier
                .width(275.dp)
                .height(56.dp)
                , // Atur border secara manual
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = outlineColor, // Background putih
                contentColor = Color.White // Warna teks biru
            )
        ) {
            Text(text = "Daftar")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignupPage() {
    // Preview the SignupPage composable
    SignupPage()
}