package com.example.petepath.pages.features.reportpage

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.petepath.Screen
import com.example.petepath.UserViewModel
import com.example.petepath.data.ReportItem
import com.example.petepath.data.RouteRepository
import com.example.petepath.ui.theme.BottomBar
import com.example.petepath.ui.theme.BottomBarScreen
import com.example.petepath.ui.theme.DropdownMenuField
import kotlinx.coroutines.launch

@Composable
fun ReportPage(
    navController: NavController,
    viewModel: UserViewModel
) {
    var route by remember { mutableStateOf("") }
    var violationCategory by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var vehiclePlate by remember { mutableStateOf("") }
    val outlineColor = Color(0xFF007BFF)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val allRoutes = RouteRepository.getAllRoutes()
    val routeOptions = allRoutes.map { "Rute ${it.id} | ${it.name}" }

    val currentScreen = BottomBarScreen.Report

    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    DropdownMenuField(
                        label = "Pilih Rute",
                        options = routeOptions,
                        selectedOption = route,
                        onOptionSelected = { route = it }
                    )
                }
                item{
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
                }
                item {
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
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedBorderColor = outlineColor,
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
                }
                item {
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
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedBorderColor = outlineColor,
                            unfocusedBorderColor = outlineColor,
                            focusedLabelColor = outlineColor,
                            unfocusedLabelColor = Color.Gray
                        )
                    )
                }
                item{
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                if (route.isNotBlank() && violationCategory.isNotBlank() && description.isNotBlank() && vehiclePlate.isNotBlank()) {
                                    val routeParts = route.split(" | ")
                                    if (routeParts.size >= 2) {
                                        val routeNumber = routeParts[0].removePrefix("Rute ").trim()
                                        val routeName = routeParts[1].trim()

                                        val currentDate = java.text.SimpleDateFormat("dd MMMM yyyy, HH:mm", java.util.Locale.getDefault()).format(java.util.Date())

                                        val report = ReportItem(
                                            routeNumber = routeNumber,
                                            routeName = routeName,
                                            violationCategory = violationCategory,
                                            description = description,
                                            vehiclePlate = vehiclePlate,
                                            date = currentDate
                                        )

                                        viewModel.addReport(report)

                                        Toast.makeText(context, "Laporan berhasil dikirim", Toast.LENGTH_SHORT).show()

                                        navController.navigate(Screen.ReportHistory.route) {
                                            popUpTo(Screen.Report.route) { inclusive = true }
                                        }
                                    } else {
                                        Toast.makeText(context, "Format rute tidak valid", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Silakan lengkapi semua field", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
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
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewReportPage() {
//    // Mock NavController dan ViewModel untuk preview
//    val navController = rememberNavController()
//    val context = LocalContext.current
//    val repository = UserPreferencesRepository(context = context)
//    val viewModel = remember {
//        UserViewModel(repository = repository).apply {
//            // Tambahkan pengguna palsu untuk preview
//            saveUserData(username = "TestUser", email = "test@example.com", password = "password")
//            _currentUserEmail.value = "test@example.com"
//        }
//    }
//
//    PetePathTheme {
//        ReportPage(navController = navController, viewModel = viewModel)
//    }
