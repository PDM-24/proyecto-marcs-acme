package com.project.smartgreen.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.project.smartgreen.R

@Composable
fun imagendefondo(modifier: Modifier = Modifier) {
    Log.i("bgImagen", "bgImagen function called")
    val bgImagePainter = painterResource(id = R.drawable.bgimagerata)
    Log.i("bgImagen", "Painter resource loaded")
    Image(
        painter = bgImagePainter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}