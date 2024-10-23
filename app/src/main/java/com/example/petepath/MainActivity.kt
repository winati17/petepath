package com.example.petepath

//import LoginPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.petepath.ui.theme.OutlinedPrimaryButton
import com.example.petepath.ui.theme.PrimaryButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
//        val authViewModel : AuthViewModel by viewModels()
//        setContent {
//            FirebaseAuthDemoAppTheme{
//                Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
//                    MyAppNavigation(modifier = Modifier.padding(innerPadding), authViewModel=authViewModel)
//                }
//            }
//        }
//        setContent {
//            LoginPage()
//        }
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Column {
        PrimaryButton(onClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedPrimaryButton(onClick = { /* TODO */ })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}
