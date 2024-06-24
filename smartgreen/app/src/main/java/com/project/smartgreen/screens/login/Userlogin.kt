package com.project.smartgreen.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.smartgreen.ui.viewmodel.MainViewModel
import com.project.smartgreen.ui.components.Logo
import com.project.smartgreen.ui.components.bgImagen
import com.project.smartgreen.ui.theme.primaryGreen

@Composable
fun UserLogin(navController: NavController, viewModel: MainViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center

    ) {
        bgImagen()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Logo()

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .graphicsLayer {
                        // Aplicar el efecto de desenfoque y transparencia
                        alpha = 0.75f
                        shadowElevation = 10.dp.toPx()
                    }
                    .background(
                        color = Color.White.copy(alpha = 0.3f)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryGreen)
                ) {
                    Text(text = "Iniciar sesión", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "¿No tienes cuenta? Regístrate",
                    color = primaryGreen,
                    modifier = Modifier.clickable {
                        // TODO: Navegar a la página de registro

                    }
                )
            }
        }
    }
}
