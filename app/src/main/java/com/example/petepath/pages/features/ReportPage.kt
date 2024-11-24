package com.example.petepath.pages.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown


@Composable
fun ReportPage() {
    var route by remember { mutableStateOf("") }
    var violationCategory by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var vehiclePlate by remember { mutableStateOf("") }
    val outlineColor = Color(0xFF007BFF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Laporan",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = outlineColor // Blue color for title
            ),
            modifier = Modifier
                .padding(bottom = 5.dp, top = 30.dp)
                .align(Alignment.Start),
        )
        Spacer(modifier = Modifier.height(15.dp))
        DropdownMenuField(
            label = "Pilih Rute",
            options = listOf(
                "Rute 01 | Sudiang",
                "Rute 02 | Unhas",
                "Rute 03 | BTP",
                "Rute 04 | Pettarani"
            ),
            selectedOption = route,
            onOptionSelected = { route = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        DropdownMenuField(
            label = "Pilih Kategori Pelanggaran",
            options = listOf(
                "Mengabaikan rambu lalu lintas",
                "Mengemudi secara agresif",
                "Pelecehan",
                "Lainnya"
            ),
            selectedOption = violationCategory,
            onOptionSelected = { violationCategory = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Deskripsi Pelanggaran") },
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            singleLine = false,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,  // Transparent background
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,          // Use outlineColor here
                unfocusedBorderColor = outlineColor,
                focusedLabelColor = outlineColor,
                unfocusedLabelColor = Color.Gray
            ),
            maxLines = 20,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = vehiclePlate,
            onValueChange = { vehiclePlate = it },
            label = { Text("Plat Kendaraan") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,  // Transparent background
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = outlineColor,          // Use outlineColor here
                unfocusedBorderColor = outlineColor,
                focusedLabelColor = outlineColor,
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { /* Handle submit */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(11.dp)
        ) {
            Text(
                text = "Kirim Laporan",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown icon",
                        tint = Color(0xFF007BFF) // Adjust color to match the design
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = Color(0xFF007BFF),
                unfocusedLabelColor = Color.Gray,
                unfocusedTrailingIconColor = Color(0xFF007BFF),
                focusedBorderColor = Color(0xFF007BFF),
                unfocusedBorderColor = Color(0xFF007BFF),
                cursorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp) // Rounded corners for the outlined field
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.9f) // Membatasi lebar menjadi 90% dari layar
                .background(Color.White, shape = RoundedCornerShape(8.dp)) // Bentuk dan warna background
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    text = { Text(option, style = TextStyle(color = Color.Black)) },
                )
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportPage() {
    // Preview the ReportPage composable
    ReportPage()
}