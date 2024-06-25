package com.project.smartgreen.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun LineChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFF174D25),
    pointColor: Color = Color.Red
) {
    Canvas(modifier = modifier) {
        drawLineChart(data, lineColor, pointColor)
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawLineChart(
    data: List<Float>,
    lineColor: Color,
    pointColor: Color
) {
    if (data.isEmpty()) return

    val path = Path()
    val spacing = size.width / (data.size - 1)
    val maxValue = data.maxOrNull() ?: return
    val minValue = data.minOrNull() ?: return
    val valueRange = maxValue - minValue

    data.forEachIndexed { index, value ->
        val x = spacing * index
        val y = size.height - (value - minValue) / valueRange * size.height

        if (index == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }

        drawCircle(
            color = pointColor,
            radius = 4.dp.toPx(),
            center = Offset(x, y)
        )
    }

    drawPath(
        path = path,
        color = lineColor,
        style = Stroke(width = 4.dp.toPx())
    )
}
