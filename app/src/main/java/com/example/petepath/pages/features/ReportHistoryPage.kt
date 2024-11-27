package com.example.petepath.pages.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petepath.R

@Composable
fun ReportHistoryPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Riwayat Laporan",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF007BFF)
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.leaf),
                contentDescription = "Vector Image",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Tidak ada laporan terbaru.",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp)

            )
            Button(
                onClick = { /* Tambahkan logika klik */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                shape = RoundedCornerShape(11.dp),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = "+ Tambah Laporan",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportHistory() {
    ReportHistoryPage()
}
