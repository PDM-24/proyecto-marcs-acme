package com.project.smartgreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.bgImagen
//import com.project.smartgreen.components.Checkbox

@Composable
fun AgregadosScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    // Estados para los checkboxes
    val monitorCultivosState = remember { mutableStateOf(false) }
    val recibirAlertasState = remember { mutableStateOf(false) }
    val registroCultivosState = remember { mutableStateOf(false) }

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
                    .size(300.dp, 400.dp)
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
                    Text(text = "¡Cultivo Añadido Exitosamente!", fontSize = 18.sp, color = Color.Black)
                    Text(text = "Ahora puedes:", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(20.dp))


                    Checkbox(label = "Monitorear cultivos", checkedState = monitorCultivosState)
                    Checkbox(label = "Recibir alertas de cuidados", checkedState = recibirAlertasState)
                    Checkbox(label = "Registro de cultivos", checkedState = registroCultivosState)

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { navController.navigate("lista") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25))
                    ) {
                        Text(text = "Aceptar", color = Color.White)
                    }
                }
            }
        }
    }
}
