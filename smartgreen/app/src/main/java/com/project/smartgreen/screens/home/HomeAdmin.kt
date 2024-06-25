package com.project.smartgreen.screens.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.smartgreen.ui.components.bgImagen
import com.project.smartgreen.R
import com.project.smartgreen.ui.components.AddLogo
import com.project.smartgreen.ui.components.LogLogo
import com.project.smartgreen.ui.components.Logo
import com.project.smartgreen.ui.components.NotificationLogo
import com.project.smartgreen.ui.components.ProfileLogo
import com.project.smartgreen.ui.components.StatusLogo
import com.project.smartgreen.ui.components.imagendefondo


@Composable
fun HomeAdminScreen(navController: NavController){
    Box( modifier = Modifier
        .fillMaxSize()){
        imagendefondo()
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Spacer(modifier = Modifier
                .height(20.dp))
            // Logo de la app
            Box(modifier = Modifier
                .size(150.dp)
            ){
                Logo(modifier = Modifier.size(150.dp))
            }
            Column(
                modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.Center, // Centrar contenido verticalmente dentro de la Column
                horizontalAlignment = Alignment.CenterHorizontally // Centrar contenido horizontalmente dentro de la Column
            ) {
                Text(
                    text = "Bienvenido a SmartGreen",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize, // Tamaño del texto
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                //.padding(24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(
                    modifier = Modifier
                        .height(140.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    ElevatedButton(
                        onClick = {navController.navigate("mensaje") },
                        modifier = Modifier
                            .size(100.dp)  // Square size
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium,  // Rounded corners
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 6.dp, // Elevación por defecto
                            pressedElevation = 10.dp, // Elevación cuando el botón es presionado
                            disabledElevation = 0.dp // Elevación cuando el botón está deshabilitado
                        ),
                    ) {
                        NotificationLogo(modifier = Modifier.size(300.dp))
                    }
                    Text("Notificaciones")
                }

                Column(
                    modifier = Modifier
                        .height(140.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    ElevatedButton(
                        onClick = {navController.navigate("useradmin") },
                        modifier = Modifier
                            .size(100.dp)  // Square size
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium, // Rounded corners
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 6.dp, // Elevación por defecto
                            pressedElevation = 10.dp, // Elevación cuando el botón es presionado
                            disabledElevation = 0.dp // Elevación cuando el botón está deshabilitado
                        ),
                    ) {
                        ProfileLogo(modifier = Modifier.size(300.dp))
                    }
                    Text("Perfil")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                //.padding(24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .height(140.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    ElevatedButton(
                        onClick = {navController.navigate("informes") },
                        modifier = Modifier
                            .size(100.dp)  // Square size
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium, // Rounded corners
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 6.dp, // Elevación por defecto
                            pressedElevation = 10.dp, // Elevación cuando el botón es presionado
                            disabledElevation = 0.dp // Elevación cuando el botón está deshabilitado
                        ),
                    ) {
                        StatusLogo(modifier = Modifier.size(300.dp))
                    }
                    Text("Estado")
                }



                Column(
                    modifier = Modifier
                        .height(140.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    ElevatedButton(
                        onClick = {navController.navigate("seleccionarc") },
                        modifier = Modifier
                            .size(100.dp)  // Square size
                            .padding(8.dp),
                        shape = MaterialTheme.shapes.medium, // Rounded corners
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green)),
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 6.dp, // Elevación por defecto
                            pressedElevation = 10.dp, // Elevación cuando el botón es presionado
                            disabledElevation = 0.dp // Elevación cuando el botón está deshabilitado
                        ),
                    ) {
                        AddLogo(modifier = Modifier.size(300.dp))
                    }
                    Text("Agregar Usuario")
                }

            }




        }



    }
}