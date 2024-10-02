package com.example.petepath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petepath.ui.theme.PetePathTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        val authViewModel : AuthViewModel by viewModels()
//        setContent {
//            FirebaseAuthDemoAppTheme{
//                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
//                    MyAppNavigation(modifier = Modifier.padding(innerPadding), authViewModel=authViewModel)
//                }
//            }
//        }
        setContent {
            ButtonApp()
        }
    }
}

@Composable
fun ButtonApp() {
    // Center the buttons vertically and horizontally
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Filled Button
        Button(
            onClick = { /* TODO */ },
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

        Spacer(modifier = Modifier.height(16.dp)) // Space between buttons

        // Outlined Button with BorderStroke for the border
        OutlinedButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .padding(16.dp).width(200.dp),
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ButtonApp()
}
