package com.project.smartgreen.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.InicioSesionButtons
import com.project.smartgreen.ui.components.Logo
import com.project.smartgreen.ui.components.bgImagen

@Composable
fun InicioSesionScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        bgImagen()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // Logo de la app
            Logo()


            // Contenedor con los botones y el texto
            Box(
                modifier = Modifier
                    .padding(106.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                InicioSesionButtons(navController)
            }
        }
    }
}