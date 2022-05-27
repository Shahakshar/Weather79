package com.example.weather79.presentation.screens.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather79.data.DataOrException
import com.example.weather79.model.Weather
import com.example.weather79.navigation.WeatherAppScreens
import com.example.weather79.presentation.screens.main_screen.common.HumidityWindPressureRow
import com.example.weather79.presentation.screens.main_screen.common.SunSetAndSunRiseRow
import com.example.weather79.presentation.screens.main_screen.common.WeatherStateImage
import com.example.weather79.presentation.screens.main_screen.common.WeeklyWeatherDataCard
import com.example.weather79.presentation.screens.setting_screen.SettingsViewModel
import com.example.weather79.utils.formatDate
import com.example.weather79.utils.formatDecimals
import com.example.weather79.widgets.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    city: String?,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val unitFromDb: String?
    val checkDatabase = settingsViewModel.unitList.collectAsState().value
    unitFromDb = if(checkDatabase.isEmpty()) {
        "imperial"
    } else {
        checkDatabase[0].unit
    }

//    Log.d("TAG", "MainScreen: $city")
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeather(city = city.toString(), units = unitFromDb)
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else {
        MainScaffold(weather = weatherData.data!!, navController = navController, unitFromDb = unitFromDb)
    }


}

@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavController,
    unitFromDb: String
) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.city.name + " ,${weather.city.country}",
            navController = navController,
            onAddActionClicked = {
                navController.popBackStack()
                navController.navigate(WeatherAppScreens.SearchScreen.name)
            },
            elevation = 5.dp
        )
    }) {
        MainContent(weather, unitFromDb)
    }

}

@Composable
fun MainContent(weather: Weather?, unitFromDb: String) {
    val weatherItem = weather!!.list[0]
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /** this is day month and date on top */
        Text(
            text = formatDate(weather.list[0].dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        /** this is Circle on top of screen */
        Surface(
            modifier = Modifier
                .padding(5.dp)
                .size(200.dp),
            shape = CircleShape,
            elevation = 6.dp,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(code = weatherItem.weather[0].icon)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "°",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = unitFromDb,
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.LightGray
                )
                Text(
                    text = weatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        /** this is humidity pressure and wind row below circle shape */
        HumidityWindPressureRow(weatherItem = weatherItem, unitFromDb = unitFromDb)
        Divider()

        /** this is sunset and sunrise row */
        SunSetAndSunRiseRow(weatherItem = weatherItem)

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "THIS WEEK",
            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
            fontWeight = FontWeight.Bold
        )

        /** last portion weekly weather data */
        WeeklyWeatherDataCard(weather = weather)
    }
}
