package com.project.smartgreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
//import com.aprendiendo.loginscreensmartgreen.R
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.project.smartgreen.R.drawable.gmail

@Composable
fun Gmail(modifier: Modifier = Modifier) {
    val logoPainter = painterResource(id = gmail)
    Image(
        painter = logoPainter,
        contentDescription = null,
        modifier = modifier.size(100.dp)
    )
}