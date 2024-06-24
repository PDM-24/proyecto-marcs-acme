package com.project.smartgreen.screens.graficas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aprendiendo.loginscreensmartgreen.ui.components.CustomCircularIndicator
import com.aprendiendo.loginscreensmartgreen.ui.components.ColorLegend

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraficasAdminScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val dispositivos = listOf("Dispositivo 1", "Dispositivo 2", "Dispositivo 3")
    var selectedDispositivo by remember { mutableStateOf("") }
    var expandedDispositivo by remember { mutableStateOf(false) }

    val tipoCultivo = listOf("Cultivo 1", "Cultivo 2", "Cultivo 3")
    var selectedCultivo by remember { mutableStateOf("") }
    var expandedCultivo by remember { mutableStateOf(false) }

    // Valores de los indicadores
    val estadoSustrato = 0.7f
    val nivelResequedad = 0.45f
    val calidadAire = 0.0f
    val luzRecibida = 0.3f

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
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

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCircularIndicator(value = estadoSustrato, label = "Estado del sustrato", color = Color(0xFF174D25))
                CustomCircularIndicator(value = nivelResequedad, label = "Nivel de resequedad", color = Color(0xFF174D25))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCircularIndicator(value = calidadAire, label = "Calidad del aire", color = Color(0xFF174D25))
                CustomCircularIndicator(value = luzRecibida, label = "Luz recibida", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCircularIndicator(value = 0.7f, label = "Otro indicador", color = Color.Yellow)
                CustomCircularIndicator(value = 1.0f, label = "Controlado", color = Color(0xFF174D25))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Leyenda de colores
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(8.dp)),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                ColorLegend(color = Color(0xFF174D25), label = "Excelente estado")
                ColorLegend(color = Color.Red, label = "En peligro")
                ColorLegend(color = Color.Yellow, label = "Casi en peligro")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                navController.navigate("informesgrafica")
            }) {
                Text("Ver informe")
            }
        }
    }
}
