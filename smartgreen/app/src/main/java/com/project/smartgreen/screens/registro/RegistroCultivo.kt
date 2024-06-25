package com.project.smartgreen.screens

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.project.smartgreen.data.model.Crop
import com.project.smartgreen.ui.viewmodel.MainViewModel
import com.project.smartgreen.ui.viewmodel.AddCropState
import com.project.smartgreen.ui.components.imagendefondo
import com.project.smartgreen.ui.theme.primaryGreen
import java.util.*

@Composable
fun RegistroCultivo(navController: NavController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var plantedDate by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var soilType by remember { mutableStateOf("") }
    var cropType by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val addCropState by viewModel.addCropState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            val locationListener = object : android.location.LocationListener {
                override fun onLocationChanged(loc: Location) {
                    location = "${loc.latitude}, ${loc.longitude}"
                }
                override fun onStatusChanged(provider: String?, status: Int, extras: android.os.Bundle?) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            if (isGpsEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, locationListener)
            } else if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
            }
        }
    }

    LaunchedEffect(addCropState) {
        when (addCropState) {
            is AddCropState.Success -> {
                // Navegar a la pantalla de cultivos o mostrar un mensaje de éxito
                navController.navigate("home")
            }
            is AddCropState.Error -> {
                // Mostrar mensaje de error
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        imagendefondo()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Registrar Cultivo", fontSize = 24.sp)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre del cultivo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = plantedDate,
                onValueChange = { plantedDate = it },
                label = { Text("Fecha de plantación (YYYY-MM-DD)") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {
                        val calendar = Calendar.getInstance()
                        val datePickerDialog = DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                plantedDate = "$year-${month + 1}-$dayOfMonth"
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        )
                        datePickerDialog.show()
                    }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Select Date")
                    }
                }
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Ubicación (Latitud, Longitud)") },
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            OutlinedTextField(
                value = soilType,
                onValueChange = { soilType = it },
                label = { Text("Tipo de suelo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cropType,
                onValueChange = { cropType = it },
                label = { Text("Tipo de cultivo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notas") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val crop = Crop(
                        name = name,
                        plantedDate = plantedDate,
                        location = location,
                        soilType = soilType,
                        cropType = cropType,
                        notes = notes
                    )
                    viewModel.addCrop(crop)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
            ) {
                Text(text = "Registrar", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier.fillMaxWidth(), // Navega hacia atrás en la pila de navegación
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25))
            ) {
                Text(text = "Regresar")
            }
        }
    }
}
