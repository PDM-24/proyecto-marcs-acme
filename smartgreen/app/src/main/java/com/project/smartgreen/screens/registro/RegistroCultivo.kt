package com.project.smartgreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.smartgreen.data.model.Crop
import com.project.smartgreen.ui.viewmodel.MainViewModel
import com.project.smartgreen.ui.viewmodel.AddCropState
import com.project.smartgreen.ui.components.bgImagen
import com.project.smartgreen.ui.components.imagendefondo
import com.project.smartgreen.ui.theme.primaryGreen

@Composable
fun RegistroCultivo(navController: NavController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var plantedDate by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var soilType by remember { mutableStateOf("") }
    var cropType by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    val addCropState by viewModel.addCropState.collectAsState()

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
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Ubicación (Latitud, Longitud)") },
                modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.fillMaxWidth(),// Navega hacia atrás en la pila de navegación
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25))
            ) {
                Text(text = "Regresar")
            }


        }
    }
}
