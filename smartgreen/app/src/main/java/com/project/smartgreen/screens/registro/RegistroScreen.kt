 package com.project.smartgreen.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.project.smartgreen.R
import com.project.smartgreen.ui.viewmodel.ComentariosViewModel
import com.project.smartgreen.ui.viewmodel.Registro

@Composable
fun RegistroScreen(navController: NavHostController, viewModel: ComentariosViewModel, modifier: Modifier = Modifier) {
    val comentarios = remember { mutableStateOf("") }
    val ubicacion = remember { mutableStateOf("") }
    val tipoCultivo = remember { mutableStateOf("") }
    val tipoSuelo = remember { mutableStateOf("") }
    val fechaObservacion = remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        bgImagen()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                    }
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp), // Añadir relleno alrededor del Box para más espacio
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
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
                            .height(60.dp)
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
                            .height(60.dp)
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
                            .height(60.dp)
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
                            .height(60.dp)
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
                            .height(60.dp)
                            .background(Color(0xFFEDE7F6), RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Añadir imagen", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    IconButton(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camara),
                            contentDescription = "añadir imagen"
                        )
                    }

                    selectedImageUri?.let { uri ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            val registro = Registro(
                                comentarios = comentarios.value,
                                ubicacion = ubicacion.value,
                                tipoCultivo = tipoCultivo.value,
                                tipoSuelo = tipoSuelo.value,
                                fechaObservacion = fechaObservacion.value,
                                imagenUri = selectedImageUri?.toString()
                            )
                            viewModel.addRegistro(registro)
                            navController.navigate("comentarios")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF174D25))
                    ) {
                        Text(text = "Añadir Registro")
                    }
                }
            }
        }
    }
}