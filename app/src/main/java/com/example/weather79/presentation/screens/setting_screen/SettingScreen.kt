package com.example.weather79.presentation.screens.setting_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather79.model.Unit
import com.example.weather79.ui.theme.TopYellow
import com.example.weather79.widgets.WeatherAppBar

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val value = viewModel.unitList.collectAsState().value

    var choiceFromDb = if (!value.isNullOrEmpty())
        value[0].unit.lowercase()
    else
        "imperial"

    val icon1 = if (choiceFromDb == "imperial") {
        Icons.Default.Check
    } else {
        Icons.Default.Close
    }

    val icon2 = if (choiceFromDb == "metric")
        Icons.Default.Check
    else
        Icons.Default.Close

    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Settings",
                isMainScreen = false,
                icon = Icons.Default.ArrowBack
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Click Any Of the Button",
                fontWeight = FontWeight.SemiBold,
                fontStyle = MaterialTheme.typography.h5.fontStyle,
                modifier = Modifier.padding(6.dp)
            )
            IconButton(
                onClick = {
                    choiceFromDb = "imperial"
                    viewModel.deleteAllUnits()
                    viewModel.insertUnit(Unit(choiceFromDb))
                },
                modifier = Modifier
                    .padding(6.dp)
                    .clip(shape = RectangleShape)
                    .background(TopYellow.copy(alpha = 0.7f))
            ) {
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(text = "Imperial (°F)")
                    Icon(
                        imageVector = icon1,
                        contentDescription = "icons"
                    )
                }
            }
            IconButton(
                onClick = {
                    choiceFromDb = "metric"
                    viewModel.deleteAllUnits()
                    viewModel.insertUnit(Unit(choiceFromDb))
                },
                modifier = Modifier
                    .padding(6.dp)
                    .clip(shape = RectangleShape)
                    .background(TopYellow.copy(alpha = 0.7f))
            ) {
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(text = "Metric (°C)")
                    Icon(
                        imageVector = icon2,
                        contentDescription = "icons"
                    )
                }
            }
        }
    }
}