package com.example.petepath.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//            OutlinedPrimaryButton(
//                onClick = { /* TODO: Handle button click */ },
//                modifier = Modifier.fillMaxWidth(),
//                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF007BFF)),
//                border = BorderStroke(2.dp, Color(0xFF007BFF))
//            )
fun PrimaryButton(onClick: () -> Unit, text : String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)), // Blue background color (007BFF)
        shape = MaterialTheme.shapes.medium, // Rounded corner
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun OutlinedPrimaryButton(onClick: () -> Unit, text : String) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, Color(0xFF007BFF)), // Blue border with 2.dp width
        shape = MaterialTheme.shapes.medium // Rounded corners
    ) {
        Text(
            text = text,
            color = Color(0xFF007BFF), // Blue text
            fontSize = 10.sp, // Font size
            fontWeight = FontWeight.Bold // Bold text
        )
    }
}

@Composable
fun ViewButton() {
    Column {
        PrimaryButton(onClick = { /* TODO */ }, text ="Button")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedPrimaryButton(onClick = { /* TODO */ }, text ="Button")
    }
}

@Preview(showBackground = true)
@Composable
fun ViewButtonPreview() {
    ViewButton()
}