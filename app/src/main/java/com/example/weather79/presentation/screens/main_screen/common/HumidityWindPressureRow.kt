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
import com.example.weather79.presentation.screens.setting_screen.SettingsViewModel

@Composable
fun HumidityWindPressureRow(
    weatherItem: WeatherItem,
    unitFromDb: String
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${weatherItem.humidity}%",
                style = MaterialTheme.typography.caption
            )
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.gauge),
                contentDescription = "pressure icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${weatherItem.pressure} psi",
                style = MaterialTheme.typography.caption
            )
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${weatherItem.speed}" + if(unitFromDb == "imperial") " mph" else " m/s",
                style = MaterialTheme.typography.caption
            )
        }
    }
}
