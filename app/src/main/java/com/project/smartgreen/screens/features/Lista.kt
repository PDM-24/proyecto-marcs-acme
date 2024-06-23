package com.project.smartgreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.smartgreen.R
import com.project.smartgreen.ui.components.Flor
import com.project.smartgreen.ui.components.Logo

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val dispositivos = listOf("Dispositivo 1", "Dispositivo 2", "Dispositivo 3")
    var selectedDispositivo by remember { mutableStateOf("") }
    var expandedDispositivo by remember { mutableStateOf(false) }


    val tipoCultivo = listOf("Cultivo 1", "Cultivo 2", "Cultivo 3")
    var selectedCultivo by remember { mutableStateOf("") }
    var expandedCultivo by remember { mutableStateOf(false) }


    val dispositivosDisponibles = listOf("Sensor 1", "Sensor 2", "Sensor 3")
    var selectedSensor by remember { mutableStateOf("") }
    var expandedSensor by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Logo()
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
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
                        tipoCultivo.forEach { cultivo ->
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

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Seleccionar Sensor")
                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown para Tipo de Cultivo
                ExposedDropdownMenuBox(
                    expanded = expandedSensor,
                    onExpandedChange = { expandedSensor = !expandedSensor }
                ) {
                    TextField(
                        value = selectedSensor,
                        onValueChange = { selectedSensor = it },
                        readOnly = true,
                        label = { Text("Seleccionar sensor") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandedSensor
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedSensor,
                        onDismissRequest = { expandedSensor = false }
                    ) {
                        dispositivosDisponibles.forEach { sensor ->
                            DropdownMenuItem(
                                text = { Text(sensor) },
                                onClick = {
                                    selectedSensor = sensor
                                    expandedSensor = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Flor(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .graphicsLayer {
                    translationX = 30.dp.toPx()
                    translationY = 30.dp.toPx()
                    scaleX = 1.2f
                    scaleY = 1.2f
                }
        )
    }
}