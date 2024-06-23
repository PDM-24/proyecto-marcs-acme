<<<<<<< HEAD
package com.project.smartgreen.screens.SeleccionScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
=======
package com.project.smartgreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
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

<<<<<<< HEAD

=======
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
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
        bgImagen()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
<<<<<<< HEAD
            Spacer(modifier = Modifier.height(50.dp)) // Espacio para mover el logo más abajo
=======
            Spacer(modifier = Modifier.height(50.dp))
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
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
<<<<<<< HEAD
                    .background(Color.White.copy(alpha = 0.8f)),
=======
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp), // Añadir relleno alrededor del Box para más espacio
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Dispositivo")
                    Spacer(modifier = Modifier.height(8.dp))

                    // Dropdown para Dispositivo
<<<<<<< HEAD
                    Box(modifier = Modifier.fillMaxWidth()) {
=======
                    ExposedDropdownMenuBox(
                        expanded = expandedDispositivo,
                        onExpandedChange = { expandedDispositivo = !expandedDispositivo }
                    ) {
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
                        TextField(
                            value = selectedDispositivo,
                            onValueChange = { selectedDispositivo = it },
                            readOnly = true,
                            label = { Text("Seleccionar dispositivo") },
<<<<<<< HEAD
                            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expandedDispositivo = !expandedDispositivo }
                        )
                        DropdownMenu(
                            expanded = expandedDispositivo,
                            onDismissRequest = { expandedDispositivo = false },
                            modifier = Modifier.fillMaxWidth()
=======
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
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
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
<<<<<<< HEAD
                    Box(modifier = Modifier.fillMaxWidth()) {
=======
                    ExposedDropdownMenuBox(
                        expanded = expandedCultivo,
                        onExpandedChange = { expandedCultivo = !expandedCultivo }
                    ) {
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
                        TextField(
                            value = selectedCultivo,
                            onValueChange = { selectedCultivo = it },
                            readOnly = true,
                            label = { Text("Seleccionar cultivo") },
<<<<<<< HEAD
                            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expandedCultivo = !expandedCultivo }
                        )
                        DropdownMenu(
                            expanded = expandedCultivo,
                            onDismissRequest = { expandedCultivo = false },
                            modifier = Modifier.fillMaxWidth()
=======
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
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
                        ) {
                            cultivos.forEach { cultivo ->
                                DropdownMenuItem(
                                    text = { Text(cultivo) },
                                    onClick = {
                                        selectedCultivo = cultivo
<<<<<<< HEAD

=======
                                        expandedCultivo = false
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
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
