package com.project.smartgreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.project.smartgreen.R

@Composable
fun bgImagen(modifier: Modifier = Modifier) {
    val bgImagePainter = painterResource(id = R.drawable.bgimage)
    Image(
        painter = bgImagePainter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight()
    )
}

