package com.project.smartgreen.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroCScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val comentarios = remember { mutableStateOf("") }
    val ubicacion = remember { mutableStateOf("") }
    val tipoSuelo = remember { mutableStateOf("") }
    val fechaObservacion = remember { mutableStateOf("") }
    val tipoCultivo = remember { mutableStateOf("") }

    val Cultivo = listOf("Cultivo 1", "Cultivo 2", "Cultivo 3")
    var selectedCultivo by remember { mutableStateOf("") }
    var expandedCultivo by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        bgImagen()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Corrected line
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp, 600.dp)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                    }
                    .background(Color.White.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Tipo de Cultivo", fontSize = 14.sp, color = Color.Gray)

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
                            Cultivo.forEach { cultivo ->
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Comentarios", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = comentarios.value,
                        onValueChange = { comentarios.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Ubicación", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = ubicacion.value,
                        onValueChange = { ubicacion.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Tipo de cultivo", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = tipoCultivo.value,
                        onValueChange = { tipoCultivo.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Tipo de suelo", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = tipoSuelo.value,
                        onValueChange = { tipoSuelo.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Hora y fecha de la observación", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = fechaObservacion.value,
                        onValueChange = { fechaObservacion.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}