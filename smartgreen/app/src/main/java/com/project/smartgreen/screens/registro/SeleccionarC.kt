package com.project.smartgreen.screens.registro


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.project.smartgreen.R
import com.project.smartgreen.ui.components.bgImagen
import com.project.smartgreen.ui.components.imagendefondo

@Composable
fun SeleccionarC(navController: NavController) {
    val context = LocalContext.current
    var selectedOption by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        imagendefondo()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dispositivo",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "SmartDetectorV1",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tipo de cultivo:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = selectedOption,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "¿Qué tipo de cultivos tendrás?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            CultivosGrid { option ->
                selectedOption = option
                Toast.makeText(context, "Opción seleccionada: $option", Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (selectedOption.isNotEmpty()) {
                        navController.navigate("registrocultivo/$selectedOption")
                    } else {
                        Toast.makeText(context, "Selecciona una opción", Toast.LENGTH_SHORT).show()
                    }
                },
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}


@Composable
fun CultivosGrid(onOptionSelected: (String) -> Unit) {
    val items = listOf(
        R.drawable.hortalizas to "Hierbas y especias",
        R.drawable.hortalizas to "Vegetales y hortalizas",
        R.drawable.legumbre to "Legumbres",
        R.drawable.papa to "Cultivos de raíz"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Add space between rows
    ) {
        for (i in items.chunked(2)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Add space between cards
            ) {
                for ((image, text) in i) {
                    CultivoCard(image = image, text = text, onOptionSelected = onOptionSelected)
                }
            }
        }
    }
}

@Composable
fun CultivoCard(image: Int, text: String, onOptionSelected: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onOptionSelected(text)
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = text,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
