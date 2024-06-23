package com.project.smartgreen.screens.mensajes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import coil.compose.rememberAsyncImagePainter
import com.project.smartgreen.ui.components.Logo
import com.project.smartgreen.ui.viewmodel.ComentariosViewModel
import com.project.smartgreen.ui.viewmodel.Registro

@Composable
fun ComentariosScreen(navController: NavHostController, viewModel: ComentariosViewModel) {
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                items(viewModel.registros) { registro ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Comentarios: ${registro.comentarios}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Ubicación: ${registro.ubicacion}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Tipo de Cultivo: ${registro.tipoCultivo}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Tipo de Suelo: ${registro.tipoSuelo}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Fecha de Observación: ${registro.fechaObservacion}",
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        registro.imagenUri?.let { uri ->
                            Image(
                                painter = rememberAsyncImagePainter(uri),
                                contentDescription = null,
                                modifier = Modifier.size(200.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
