package com.example.weather79.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather79.navigation.WeatherAppScreens

@Composable
fun ShowSettingDropDownMenu(
    navController: NavController,
    showMenu: MutableState<Boolean>,
) {
    val items = listOf("About", "Favorites", "Settings")
    DropdownMenu(
        expanded = showMenu.value,
        onDismissRequest = { showMenu.value = false },
        modifier = Modifier
            .width(140.dp)
            .background(Color.White)
    ) {
        items.forEachIndexed { _, text ->
            DropdownMenuItem(onClick = {}) {
                Icon(
                    imageVector = when (text) {
                        "About" -> Icons.Default.Info
                        "Favorites" -> Icons.Default.Favorite
                        else -> Icons.Default.Settings
                    },
                    contentDescription = null,
                    tint = Color.LightGray
                )
                Text(
                    text = text, modifier = Modifier.clickable {
                        when (text) {
                            "About" -> navController.navigate(WeatherAppScreens.AboutScreen.name)
                            "Favorites" -> navController.navigate(WeatherAppScreens.FavoriteScreen.name)
                            else -> navController.navigate(WeatherAppScreens.SettingScreen.name)
                        }
                    },
                    fontWeight = FontWeight.W300
                )
            }
        }
    }
}