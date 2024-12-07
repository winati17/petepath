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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.petepath.Screen

@Composable
fun ReportPage(navController: NavController) {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp, top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Screen.ReportHistory.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp)
                )
            }
            Text(
                text = "Laporan",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = outlineColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 2.dp)
            )
        }

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
                .height(240.dp)
                .padding(horizontal = 10.dp),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
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
                .height(50.dp)
                .padding(horizontal = 10.dp),
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
                .padding(vertical = 4.dp, horizontal = 10.dp),
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
                .fillMaxWidth(0.9f)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(start = 8.dp, end = 8.dp, top = 8.dp) // Remove bottom padding
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = { Text(option, style = TextStyle(color = Color.Black, fontSize = 14.sp)) },
                )
                if (index != options.lastIndex) {
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp) // Divider for all items except the last one
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReportPage() {
    ReportPage(navController = rememberNavController())
}
