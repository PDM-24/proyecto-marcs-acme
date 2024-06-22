package com.project.smartgreen.screens.registro


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import com.project.smartgreen.ui.components.bgImagen

@Composable
fun RegistroScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val comentarios = remember { mutableStateOf("") }
    val ubicacion = remember { mutableStateOf("") }
    val tipoCultivo = remember { mutableStateOf("") }
    val tipoSuelo = remember { mutableStateOf("") }
    val fechaObservacion = remember { mutableStateOf("") }

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
                    .size(300.dp, 550.dp)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                    }
                    .background(Color.White.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Comentarios", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = comentarios.value,
                        onValueChange = { comentarios.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Ubicación", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = ubicacion.value,
                        onValueChange = { ubicacion.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Tipo de cultivo", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = tipoCultivo.value,
                        onValueChange = { tipoCultivo.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Tipo de suelo", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = tipoSuelo.value,
                        onValueChange = { tipoSuelo.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Hora y fecha de la observación", fontSize = 14.sp, color = Color.Gray)
                    TextField(
                        value = fechaObservacion.value,
                        onValueChange = { fechaObservacion.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Añadir imagen", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    IconButton(
                        onClick = { /* Acción para añadir imagen */ },
                        modifier = Modifier.size(48.dp)
                    ) {
                        //Icon(imageVector = Icons.Default.AddAPhoto, contentDescription = "imagen")
                    }


                    Button(
                        onClick = { navController.navigate("registroc") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25))
                    ) {
                        Text(text = "Añadir Registro")
                    }
                }
            }
        }
    }
}
