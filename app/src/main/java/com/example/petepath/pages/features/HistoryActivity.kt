package com.example.petepath.pages.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petepath.R

@Composable
fun HistoryActivity(){
    val mainColor = Color(0xFF007BFF)
//    val customFontFamily = FontFamily(
//        Font(R.font.inter)
//    )
    val petepete: Painter = painterResource(id = R.drawable.vector_pete2)

    //Header Text
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 45.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Start
    ) {
        Text(
            text = "Riwayat Kegiatan",
            color = mainColor,
//            fontFamily = customFontFamily,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Start).padding(start = 45.dp)
        )

        Spacer(modifier = Modifier.height(45.dp))

//      box2
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Box(
                modifier = Modifier
                    .border(
                        3.dp,
                        color = mainColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .width(300.dp)
                    .height(120.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ){
//          isi dari box
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
//              isi dari box (ikon & text, text bawah) di sini...
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), // Add padding around the Row
                        horizontalArrangement = Arrangement.SpaceBetween, // Spread content evenly
                        verticalAlignment = Alignment.CenterVertically
                    ){
//                    ikon & text di sini...
                        Image(
                            painter = petepete,
                            contentDescription = "Pete-Pete",
                            modifier = Modifier
                                .size(90.dp)
                                .padding(start = 16.dp),
                        )
                        Column {
                            Text(
                                text = "Rute 01 | Sudiang",
                                color = mainColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                            )
                            Text(
                                text = "Rp 2.500,-",
                                color = mainColor
                            )
                        }
                    }
                    Text(
//                    text di paling bawah di sini...
                        text = "22 Oktober 2024, 09:09",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .border(
                        3.dp,
                        color = mainColor,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .width(300.dp)
                    .height(120.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ){
//          isi dari box
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
//              isi dari box (ikon & text, text bawah) di sini...
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(), // Add padding around the Row
                        horizontalArrangement = Arrangement.SpaceBetween, // Spread content evenly
                        verticalAlignment = Alignment.CenterVertically
                    ){
//                    ikon & text di sini...
                        Image(
                            painter = petepete,
                            contentDescription = "Pete-Pete",
                            modifier = Modifier
                                .size(90.dp)
                                .padding(start = 16.dp),
                        )
                        Column {
                            Text(
                                text = "Rute 02 | Unhas",
                                color = mainColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(end = 16.dp)
                            )
                            Text(
                                text = "Rp 2.500,-",
                                color = mainColor
                            )
                        }
                    }
                    Text(
//                    text di paling bawah di sini...
                        text = "5 November 2024, 09:09",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }

//    bottomappbar di sini...
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HistoryActivityPreview(){
    HistoryActivity()
}