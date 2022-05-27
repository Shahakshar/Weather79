package com.example.weather79.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather79.presentation.screens.about_screen.AboutScreen
import com.example.weather79.presentation.screens.favorite_screen.FavoriteScreen
import com.example.weather79.presentation.screens.main_screen.MainScreen
import com.example.weather79.presentation.screens.main_screen.MainViewModel
import com.example.weather79.presentation.screens.search_screen.SearchScreen
import com.example.weather79.presentation.screens.setting_screen.SettingScreen
import com.example.weather79.presentation.screens.splash_screen.WeatherSplashScreen

@ExperimentalComposeUiApi
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherAppScreens.SplashScreen.name) {
        composable(route = WeatherAppScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }
        composable(route = WeatherAppScreens.MainScreen.name+"/{city}",
        arguments = listOf(
            navArgument(name = "city") {
                type = NavType.StringType
            }
        )) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController,mainViewModel, city = city)
            }
        }
        composable(route = WeatherAppScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(route = WeatherAppScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(route = WeatherAppScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
        composable(route = WeatherAppScreens.SettingScreen.name) {
            SettingScreen(navController = navController)
        }
    }

}