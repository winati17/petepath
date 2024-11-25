
package com.example.petepath.pages.auth

//import android.graphics.drawable.shapes.Shape
//import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.tv.material3.OutlinedButtonDefaults

@Composable
fun LoginPage(){
    val mainColor = Color(0xFF007BFF)
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally //column will take full size
    ) {
        Text(text = "Halo, Selamat Datang! 👋", fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
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

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Password")
        },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = mainColor,
                unfocusedBorderColor = mainColor
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {},
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
            Text(text = "Daftar sekarang!", modifier = Modifier.clickable {  }, color = mainColor)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPagePreview(){
    LoginPage()
}

