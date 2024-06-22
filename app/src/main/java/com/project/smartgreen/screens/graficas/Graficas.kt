package com.project.smartgreen.screens.graficas


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun GraficasCScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Dispositivo", fontSize = 14.sp, color = Color.Gray)

            Button(
                onClick = { /* Acción para seleccionar dispositivo */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Seleccionar Dispositivo")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Tipo de Cultivo", fontSize = 14.sp, color = Color.Gray)

            Button(
                onClick = { /* Acción para seleccionar cultivo */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Seleccionar Cultivo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCircularIndicator(value = 0.7f, label = "Estado del sustrato", color = Color(0xFF174D25))
                CustomCircularIndicator(value = 0.45f, label = "Nivel de resequedad", color = Color(0xFF174D25))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomCircularIndicator(value = 0.0f, label = "Calidad del aire", color = Color(0xFF174D25))
                CustomCircularIndicator(value = 0.3f, label = "Luz recibida", color = Color.Red)
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
        }
    }
}

@Composable
fun CustomCircularIndicator(value: Float, label: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(100.dp)) {
                val backgroundCircleColor = Color.LightGray.copy(alpha = 0.3f)
                val strokeWidth = 15f

                drawArc(
                    color = backgroundCircleColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )

                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * value,
                    useCenter = false,
                    style = Stroke(width = strokeWidth)
                )
            }

            Text(
                text = "${(value * 100).toInt()}%",
                fontSize = 20.sp,
                color = color,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun ColorLegend(color: Color, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(24.dp)) {
            drawRect(color = color)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}
