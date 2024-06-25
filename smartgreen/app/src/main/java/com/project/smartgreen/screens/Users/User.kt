package com.project.smartgreen.screens.Users


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.project.smartgreen.R
import com.project.smartgreen.ui.viewmodel.ComentariosViewModel

@Composable
fun UserScreen(navController: NavHostController, viewModel: ComentariosViewModel, modifier: Modifier = Modifier) {

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
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Gray, CircleShape)
                    .clip(CircleShape)
                    .clickable {
                        launcher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    Icon(
                        painter = painterResource(id = R.drawable.camara),
                        contentDescription = "añadir imagen",
                        modifier = Modifier.size(50.dp),
                        tint = Color.White
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(selectedImageUri),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nombre Completo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Editar Foto",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    launcher.launch("image/*")
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                    }
                    .background(Color.White.copy(alpha = 0.8f))
                    .padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Cuenta Registrada:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "cuentaejemplo@dominio.com", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Cumpleaños:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "30/Febrero", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun bgImagen() {
    // Placeholder function for background image
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFEFEFEF)))
}
