package com.project.smartgreen.screens.graficas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.LineChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformesGraficaScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val dispositivos = listOf("SmartDetectorV1", "SmartDetectorV2", "SmartDetectorV3")
    var selectedDispositivo by remember { mutableStateOf(dispositivos[0]) }
    var expandedDispositivo by remember { mutableStateOf(false) }

    var selectedRange by remember { mutableStateOf("Meses") }

    // Datos del dispositivo 1
    val estadoSustratoDataMesesV1 = listOf(25f, 26f, 27f, 28f, 29f, 27f, 26f, 28f)
    val nivelResequedadDataMesesV1 = listOf(30f, 28f, 29f, 27f, 26f, 25f, 27f, 28f)

    val estadoSustratoDataDiasV1 = listOf(25f, 25.5f, 26f, 26.5f, 27f, 27.5f, 28f)
    val nivelResequedadDataDiasV1 = listOf(30f, 29.5f, 29f, 28.5f, 28f, 27.5f, 27f)

    // Datos del dispositivo 2
    val estadoSustratoDataMesesV2 = listOf(24f, 25f, 26f, 27f, 28f, 26f, 25f, 27f)
    val nivelResequedadDataMesesV2 = listOf(29f, 27f, 28f, 26f, 25f, 24f, 26f, 27f)

    val estadoSustratoDataDiasV2 = listOf(24f, 24.5f, 25f, 25.5f, 26f, 26.5f, 27f)
    val nivelResequedadDataDiasV2 = listOf(29f, 28.5f, 28f, 27.5f, 27f, 26.5f, 26f)

    // Datos del dispositivo 3
    val estadoSustratoDataMesesV3 = listOf(23f, 24f, 25f, 26f, 27f, 25f, 24f, 26f)
    val nivelResequedadDataMesesV3 = listOf(28f, 26f, 27f, 25f, 24f, 23f, 25f, 26f)

    val estadoSustratoDataDiasV3 = listOf(23f, 23.5f, 24f, 24.5f, 25f, 25.5f, 26f)
    val nivelResequedadDataDiasV3 = listOf(28f, 27.5f, 27f, 26.5f, 26f, 25.5f, 25f)

    // Seleccionar los datos correspondientes según el dispositivo y el rango de tiempo
    val estadoSustratoData by remember(selectedDispositivo, selectedRange) {
        derivedStateOf {
            when (selectedDispositivo) {
                "SmartDetectorV1" -> if (selectedRange == "Meses") estadoSustratoDataMesesV1 else estadoSustratoDataDiasV1
                "SmartDetectorV2" -> if (selectedRange == "Meses") estadoSustratoDataMesesV2 else estadoSustratoDataDiasV2
                else -> if (selectedRange == "Meses") estadoSustratoDataMesesV3 else estadoSustratoDataDiasV3
            }
        }
    }

    val nivelResequedadData by remember(selectedDispositivo, selectedRange) {
        derivedStateOf {
            when (selectedDispositivo) {
                "SmartDetectorV1" -> if (selectedRange == "Meses") nivelResequedadDataMesesV1 else nivelResequedadDataDiasV1
                "SmartDetectorV2" -> if (selectedRange == "Meses") nivelResequedadDataMesesV2 else nivelResequedadDataDiasV2
                else -> if (selectedRange == "Meses") nivelResequedadDataMesesV3 else nivelResequedadDataDiasV3
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Botón para seleccionar dispositivo
            ExposedDropdownMenuBox(
                expanded = expandedDispositivo,
                onExpandedChange = { expandedDispositivo = !expandedDispositivo }
            ) {
                TextField(
                    value = selectedDispositivo,
                    onValueChange = { selectedDispositivo = it },
                    readOnly = true,
                    label = { Text("Dispositivo") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedDispositivo)
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

            Spacer(modifier = Modifier.height(16.dp))

            // Botones para seleccionar rango de tiempo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { selectedRange = "Meses" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedRange == "Meses") Color.Green else Color.Gray
                    )
                ) {
                    Text("Meses", color = Color.White)
                }

                Button(
                    onClick = { selectedRange = "Días" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedRange == "Días") Color.Green else Color.Gray
                    )
                ) {
                    Text("Días", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Gráfico de estado del sustrato
            Text(
                text = "Registros ${if (selectedRange == "Meses") "mensuales" else "diarios"}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LineChart(
                data = estadoSustratoData,
                lineColor = Color(0xFF174D25),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = "Estado del sustrato",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Gráfico de nivel de resequedad
            LineChart(
                data = nivelResequedadData,
                lineColor = Color(0xFF174D25),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(
                text = "Nivel de resequedad",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}
