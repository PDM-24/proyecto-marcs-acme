package com.project.smartgreen.ui.components


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
import com.project.smartgreen.R
import com.project.smartgreen.ui.viewmodel.ComentariosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformesScreen(navController: NavHostController, viewModel: ComentariosViewModel, modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)) // Imagen de fondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Estadísticas",
                fontSize = 24.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                    Text(
                        text = "Seleccionar Usuario:",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = "cuentaejemplo",
                        onValueChange = {},
                        readOnly = true,
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Black,
                            disabledLabelColor = Color.Gray,
                            disabledIndicatorColor = Color.Gray
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = { navController.navigate("graficasadmin")
                        // Acción del botón
                    }) {
                        Text("Ver estadísticas")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.gmail),
                        contentDescription = "Logo",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}
