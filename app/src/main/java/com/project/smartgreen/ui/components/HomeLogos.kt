package com.project.smartgreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.smartgreen.R

@Composable
fun NotificationLogo(modifier: Modifier = Modifier){
    val logoPainter = painterResource(id = R.drawable.notifications)
    Image(
        painter = logoPainter, // ID de tu logo en drawable
        contentDescription = "notificationsLogo",
        modifier = Modifier.size(300.dp) // Tamaño del logo
    )
}

@Composable
fun AddLogo(modifier: Modifier = Modifier){
    val logoPainter = painterResource(id = R.drawable.add_circle)
    Image(
        painter = logoPainter, // ID de tu logo en drawable
        contentDescription = "addCultiveLogo",
        modifier = Modifier.size(300.dp) // Tamaño del logo
    )
}

@Composable
fun StatusLogo(modifier: Modifier = Modifier){
    val logoPainter = painterResource(id = R.drawable.status)
    Image(
        painter = logoPainter, // ID de tu logo en drawable
        contentDescription = "statusLogo",
        modifier = Modifier.size(300.dp) // Tamaño del logo
    )
}

@Composable
fun LogLogo(modifier: Modifier = Modifier){
    val logoPainter = painterResource(id = R.drawable.log)
    Image(
        painter = logoPainter, // ID de tu logo en drawable
        contentDescription = "logLogo",
        modifier = Modifier.size(300.dp) // Tamaño del logo
    )
}

@Composable
fun ProfileLogo(modifier: Modifier = Modifier){
    val logoPainter = painterResource(id = R.drawable.profile)
    Image(
        painter = logoPainter, // ID de tu logo en drawable
        contentDescription = "profileLogo",
        modifier = Modifier.size(300.dp) // Tamaño del logo
    )
}