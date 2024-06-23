package com.aprendiendo.loginscreensmartgreen.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
