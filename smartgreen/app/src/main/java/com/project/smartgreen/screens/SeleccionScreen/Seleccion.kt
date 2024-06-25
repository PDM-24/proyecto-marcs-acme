package com.project.smartgreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.Logo
import com.project.smartgreen.ui.components.bgImagen

import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    var selectedDispositivo by remember { mutableStateOf("") }
    var expandedDispositivo by remember { mutableStateOf(false) }
    val dispositivos = listOf("Dispositivo 1", "Dispositivo 2", "Dispositivo 3")

    var selectedCultivo by remember { mutableStateOf("") }
    var expandedCultivo by remember { mutableStateOf(false) }
    val cultivos = listOf("Cultivo 1", "Cultivo 2", "Cultivo 3")

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        imagendefondo()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Logo()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                    }
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp), // Añadir relleno alrededor del Box para más espacio
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Dispositivo")
                    Spacer(modifier = Modifier.height(8.dp))

                    // Dropdown para Dispositivo
                    ExposedDropdownMenuBox(
                        expanded = expandedDispositivo,
                        onExpandedChange = { expandedDispositivo = !expandedDispositivo }
                    ) {
                        TextField(
                            value = selectedDispositivo,
                            onValueChange = { selectedDispositivo = it },
                            readOnly = true,
                            label = { Text("Seleccionar dispositivo") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedDispositivo
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expandedDispositivo,
                            onDismissRequest = { expandedDispositivo = false }
                        ) {
                            dispositivos.forEach { dispositivo ->
                                DropdownMenuItem(
                                    text = { Text(dispositivo) },
                                    onClick = {
                                        selectedDispositivo = dispositivo
                                        expandedDispositivo = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Tipo de cultivo")
                    Spacer(modifier = Modifier.height(8.dp))

                    // Dropdown para Tipo de Cultivo
                    ExposedDropdownMenuBox(
                        expanded = expandedCultivo,
                        onExpandedChange = { expandedCultivo = !expandedCultivo }
                    ) {
                        TextField(
                            value = selectedCultivo,
                            onValueChange = { selectedCultivo = it },
                            readOnly = true,
                            label = { Text("Seleccionar cultivo") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedCultivo
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expandedCultivo,
                            onDismissRequest = { expandedCultivo = false }
                        ) {
                            cultivos.forEach { cultivo ->
                                DropdownMenuItem(
                                    text = { Text(cultivo) },
                                    onClick = {
                                        selectedCultivo = cultivo
                                        expandedCultivo = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(50.dp)) // Espacio para mover el botón más abajo
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { navController.navigate("registro") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25)) // Cambia el color aquí
                ) {
                    Text(text = "Seleccionar")
                }
            }
        }
    }
}