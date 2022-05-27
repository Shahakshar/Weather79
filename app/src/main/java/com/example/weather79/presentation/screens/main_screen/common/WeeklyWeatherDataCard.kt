package com.example.weather79.presentation.screens.main_screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.weather79.model.Weather
import com.example.weather79.model.WeatherItem
import com.example.weather79.utils.formatDate
import com.example.weather79.utils.formatDecimals

@Composable
fun WeeklyWeatherDataCard(weather: Weather) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = Color.LightGray
    ) {

        LazyColumn(
            modifier = Modifier
                .padding(start = 1.dp, end = 1.dp, bottom = 2.dp)
        ) {
            items(items = weather.list) { item: WeatherItem ->
                Surface(
                    modifier = Modifier
                        .padding(bottom = 2.dp, top = 2.dp)
                        .fillMaxWidth(),
                    elevation = 6.dp,
                    shape = CircleShape,
                    color = Color.White
                ) {
                    WeatherDetailsRow(weatherItem = item)
                }
            }
        }

    }

}

@Composable
fun WeatherDetailsRow(weatherItem: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formatDate(weatherItem.dt).split(",")[0],
            modifier = Modifier.padding(start = 2.dp)
        )
        WeatherStateImage(code = weatherItem.weather[0].icon)
        Surface(
            modifier = Modifier.padding(0.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFFFC400)
        ) {
            Text(
                text = weatherItem.weather[0].description,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption
            )
        }
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color.Red.copy(0.7f),
                fontWeight = FontWeight.SemiBold
            )) {
                append(formatDecimals(weatherItem.temp.max)+"°")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Green.copy(0.7f),
                    fontWeight = FontWeight.SemiBold
                )) {
                append(formatDecimals(weatherItem.temp.min)+"°")
            }
        })
    }
}
