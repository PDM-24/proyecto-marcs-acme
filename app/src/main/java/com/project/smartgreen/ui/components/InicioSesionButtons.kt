package com.project.smartgreen.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.project.smartgreen.ui.theme.primaryGreen


@Composable
fun InicioSesionButtons(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inicia sesión como", color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("loginuser") },
            colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
        ) {
            Text(text = "Usuario")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(
            onClick = { navController.navigate("graficasc") },
            colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
        ) {
            Text("Administrador")
        }
    }
}