package com.example.petepath.pages.features

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile(){
    val mainColor = Color(0xFF007BFF)

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "Profil",
            color = mainColor,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Start).padding(start = 45.dp)
        )
        Spacer(modifier = Modifier.height(45.dp))

        Box(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF0EFEF))
                .padding(16.dp)
                .fillMaxSize()
        ){
            Column(
//                di sini isi dari box (profile & text, tombol logout)...
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
//                    di sini profile & text...
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(80.dp)
                            .background(Color(0xFFD8D8D8))
                    ){}
//                di sini isi dari box (profile & text, tombol logout)...

                    Column(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 16.dp)
                    ){
//                        di sini text....
                        Text(
                            text = "Eka Puteri",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "eka@gmail.com",
                            Modifier.clickable {  }
                        )
                    }
                }
                Button(
                    onClick ={},
                    modifier = Modifier
                        .width(230.dp),
                    shape = RoundedCornerShape(8.dp), // Ensures the button follows the same corner radius
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mainColor,
                        contentColor = Color.White
                    )
                ){
                    Text(text = "Logout")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row{
//            icon instagram di sini

            Text(
                text = "Follow us on Instagram"
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProfilePreview(){
    Profile()
}