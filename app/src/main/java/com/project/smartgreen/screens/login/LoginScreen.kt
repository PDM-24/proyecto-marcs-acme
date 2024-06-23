package com.project.smartgreen.screens.login


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.bgImagen

@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    // imagen de fondo
    Box(modifier = modifier
        .fillMaxSize()
    ) {
        bgImagen()
    }
}

