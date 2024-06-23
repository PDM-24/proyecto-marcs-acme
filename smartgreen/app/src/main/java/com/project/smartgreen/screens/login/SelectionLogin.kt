package com.project.smartgreen.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.smartgreen.ui.ViewModel.MainViewModel
import com.project.smartgreen.ui.components.Logo
import com.project.smartgreen.ui.components.bgImagen
import com.project.smartgreen.ui.theme.primaryGreen

@Composable
fun SelectionLogin(navController: NavController, viewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        bgImagen()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Logo()
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .padding(16.dp)
                    .graphicsLayer {
                        // Aplicar el efecto de desenfoque y transparencia
                        alpha = 0.75f
                        shadowElevation = 10.dp.toPx()
                    }
                    .background(
                        color = Color.White.copy(alpha = 0.3f)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { navController.navigate("UserLoginn") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
                ) {
                    Text(text = "Usuario", fontSize = 18.sp)
                }
                Button(
                    onClick = { navController.navigate("AdminLogin") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
                ) {
                    Text(text = "Administrador", fontSize = 18.sp)
                }
            }
        }
    }
}
