package com.project.smartgreen.screens.features


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.Flor
import com.project.smartgreen.ui.components.Logo


@Composable
fun ListaScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val dispositivos = remember { mutableStateOf("") }
    val tipoCultivo = remember { mutableStateOf("") }
    val dispositivosDisponibles = remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize()
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
                Text(text = "Dispositivos", fontSize = 14.sp, color = Color.Gray)
                TextField(
                    value = dispositivos.value,
                    onValueChange = { dispositivos.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(Color(0xFFE0F2F1), RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Tipo de Cultivo", fontSize = 14.sp, color = Color.Gray)
                TextField(
                    value = tipoCultivo.value,
                    onValueChange = { tipoCultivo.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(Color(0xFFE0F2F1), RoundedCornerShape(8.dp))
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Dispositivos Disponibles", fontSize = 14.sp, color = Color.Gray)
                TextField(
                    value = dispositivosDisponibles.value,
                    onValueChange = { dispositivosDisponibles.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .background(Color(0xFFE0F2F1), RoundedCornerShape(8.dp))
                        .padding(4.dp),
                    readOnly = true
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Flor(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .graphicsLayer {
                    translationX = 30.dp.toPx()
                    translationY = 30.dp.toPx()
                    scaleX = 1.2f
                    scaleY = 1.2f
                }
        )
    }
}
