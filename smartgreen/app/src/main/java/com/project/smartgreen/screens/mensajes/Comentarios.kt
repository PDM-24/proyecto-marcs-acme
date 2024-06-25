package com.project.smartgreen.screens.features

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.smartgreen.data.model.Comment
import com.project.smartgreen.ui.components.imagendefondo
import com.project.smartgreen.ui.viewmodel.CommentState
import com.project.smartgreen.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCommentsScreen(navController: NavHostController, viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getComments()
    }
    val commentsState by viewModel.commentsState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comentarios") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            when (val state = commentsState) {
                is CommentState.Loaded -> {

                    imagendefondo()
                    LazyColumn(
                        contentPadding = paddingValues,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.comments) { comment ->
                            CommentCard(comment)
                        }
                    }
                }
                is CommentState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = state.message, color = MaterialTheme.colorScheme.error)
                    }
                }
                is CommentState.Idle, is CommentState.Success -> {
                    // Do nothing
                }

                CommentState.Loading -> TODO()
            }
        }
    )
}

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Comentario: ${comment.comment}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Ubicación: ${comment.location}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Tipo de Cultivo: ${comment.cropType}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Tipo de Suelo: ${comment.soilType}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Fecha de Observación: ${comment.observationDateTime}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
