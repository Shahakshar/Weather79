package com.example.weather79.presentation.screens.main_screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weather79.utils.Constants

@Composable
fun WeatherStateImage(code: String) {
    val imageUrl = "${Constants.IMAGE_URL}$code.png"
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier.size(80.dp)
    )
}
