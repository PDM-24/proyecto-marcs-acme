package com.project.smartgreen.screens.features

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.smartgreen.data.model.Crop
import com.project.smartgreen.ui.components.imagendefondo
import com.project.smartgreen.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCropsScreen(navController: NavHostController, viewModel: MainViewModel) {
    // Llamar a getCrops para obtener los cultivos al cargar la pantalla
    viewModel.getCrops()
    val cropsState by viewModel.cropsState.collectAsState()


    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Cultivos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            imagendefondo()
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cropsState) { crop ->
                    CropCard(crop)
                }
            }
        }
    )
}

@Composable
fun CropCard(crop: Crop) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nombre: ${crop.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Fecha de Plantación: ${crop.plantedDate}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Ubicación: ${crop.location}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Tipo de Suelo: ${crop.soilType}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Tipo de Cultivo: ${crop.cropType}", style = MaterialTheme.typography.bodySmall)
            crop.notes?.let {
                Text(text = "Notas: $it", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
