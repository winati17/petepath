package com.example.petepath.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)), // Blue background color (007BFF)
        shape = MaterialTheme.shapes.medium // Rounded corners
    ) {
        Text(
            text = "Button",
            color = Color.White, // White text
            fontSize = 18.sp, // Font size
            fontWeight = FontWeight.Bold // Bold text
        )
    }
}

@Composable
fun OutlinedPrimaryButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .width(200.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF007BFF)), // Blue text color
        border = BorderStroke(2.dp, Color(0xFF007BFF)), // Blue border with 2.dp width
        shape = MaterialTheme.shapes.medium // Rounded corners
    ) {
        Text(
            text = "Button",
            color = Color(0xFF007BFF), // Blue text
            fontSize = 18.sp, // Font size
            fontWeight = FontWeight.Bold // Bold text
        )
    }
}