package com.project.smartgreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
<<<<<<< HEAD

=======
//import com.aprendiendo.loginscreensmartgreen.R
>>>>>>> 548797a7da2cada0393c57f648c6cdd63e5f6e1b
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.project.smartgreen.R.drawable.imagen

@Composable
fun Flor(modifier: Modifier = Modifier) {
    val logoPainter = painterResource(id = imagen)
    Image(
        painter = logoPainter,
        contentDescription = null,
        modifier = modifier.size(100.dp)
    )
}