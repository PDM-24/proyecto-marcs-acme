package com.project.smartgreen.screens.bitacora

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aprendiendo.loginscreensmartgreen.ui.components.bgImagen
import com.project.smartgreen.ui.components.bgImagen

@Composable
fun BitacoraScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        bgImagen()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp, 300.dp)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                    }
                    .background(Color.White.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Bitacora")
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { navController.navigate("seleccion") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25)) // Color verde esmeralda
                    ) {
                        Text(text = "Registrar nuevos comentarios")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { navController.navigate("agregados") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25)) // Cambia el color aquí
                    ) {
                        Text(text = "Ver comentarios existentes")
                    }
                }
            }
        }
    }
}