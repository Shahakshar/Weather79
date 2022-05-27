package com.example.weather79.presentation.screens.main_screen.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather79.R
import com.example.weather79.model.WeatherItem
import com.example.weather79.utils.formatDateTime

@Composable
fun SunSetAndSunRiseRow(weatherItem: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise icon",
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = formatDateTime(weatherItem.sunrise),
                style = MaterialTheme.typography.caption
            )
        }
        Row {
            Text(
                text = formatDateTime(weatherItem.sunset),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset icon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}