package com.project.smartgreen.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.project.smartgreen.R
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Log.i("logoimagen", "logo function called")
    val logoPainter = painterResource(id = R.drawable.logoapp)
    Image(
        painter = logoPainter,
        contentDescription = null,
        modifier = modifier.size(100.dp)
    )
}