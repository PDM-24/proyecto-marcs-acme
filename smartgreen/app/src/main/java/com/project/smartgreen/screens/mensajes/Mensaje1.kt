package com.project.smartgreen.screens.mensajes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.text.style.TextAlign
import com.project.smartgreen.ui.components.Logo

@Composable
fun Mensaje1Screen(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val estadoSustrato = backStackEntry?.savedStateHandle?.get<Float>("estadoSustrato") ?: 0f
    val nivelResequedad = backStackEntry?.savedStateHandle?.get<Float>("nivelResequedad") ?: 0f
    val calidadAire = backStackEntry?.savedStateHandle?.get<Float>("calidadAire") ?: 0f
    val luzRecibida = backStackEntry?.savedStateHandle?.get<Float>("luzRecibida") ?: 0f

    val alertMessage = getAlertMessage(estadoSustrato, nivelResequedad, calidadAire, luzRecibida)

    Box(
        modifier = Modifier.fillMaxSize()
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
                Text(
                    text = alertMessage,
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { navController.navigateUp() }, // Navega hacia atrás en la pila de navegación
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25))
                ) {
                    Text(text = "Regresar")
                }
            }
        }
    }
}

// Lógica para determinar el mensaje
fun getAlertMessage(estadoSustrato: Float, nivelResequedad: Float, calidadAire: Float, luzRecibida: Float): String {
    val alertMessages = listOf(
        "La calidad del aire parece haber disminuido; considera buscar un nuevo lugar para cultivar o examina detenidamente el entorno actual de tu cultivo",
        "Las plantas prosperan mejor cuando se controla la cantidad adecuada de agua.",
        "Encuentra el lugar ideal para tu cultivo considerando sus necesidades específicas de luz. Algunas prefieren sol directo, otras sombra.",
        "Evitar plagas en tus cultivos implica mantener un higiene adecuado, eliminar y reemplazar plantas infectadas, y emplear métodos de protección naturales o repelentes seguros.",
        "Vigila la presencia de gas natural en el área para tus cultivos, ajusta los niveles y mejora la ventilación si es necesario.",
        "Para el sustrato en tus cultivos, elige un material adecuado y asegúrate de mantener un drenaje apropiado para evitar el encharcamiento."
    )

    return when {
        calidadAire < 0.1f -> alertMessages[0]
        nivelResequedad < 0.5f -> alertMessages[1]
        luzRecibida < 0.3f -> alertMessages[2]
        else -> "Todo está bajo control."
    }
}