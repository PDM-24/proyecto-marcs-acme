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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.smartgreen.ui.components.bgImagen

@Composable
fun HomeScreen(navController: NavController){
    Box( modifier = Modifier
        .fillMaxSize()){
        bgImagen()

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {navController.navigate("mensaje") },
                    modifier = Modifier
                        .size(100.dp)  // Square size
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium  // Rounded corners
                ) {
                    Text("Notificaciones")
                }

                Button(
                    onClick = {navController.navigate("seleccionarc") },
                    modifier = Modifier
                        .size(100.dp)  // Square size
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium  // Rounded corners
                ) {
                    Text(" nuevo cultivo")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {navController.navigate("graficasc") },
                    modifier = Modifier
                        .size(100.dp)  // Square size
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium  // Rounded corners
                ) {
                    Text("Estado")
                }

                Button(
                    onClick = { navController.navigate("bitacora")},
                    modifier = Modifier
                        .size(100.dp)  // Square size
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium  // Rounded corners
                ) {
                    Text("Bitacora")
                }

                Button(
                    onClick = { /* TODO: Handle button 5 click */ },
                    modifier = Modifier
                        .size(100.dp)  // Square size
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium  // Rounded corners
                ) {
                    Text("Perfil")
                }
            }

        }




    }



}