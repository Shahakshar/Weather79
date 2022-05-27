package com.example.weather79.presentation.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weather79.R
import com.example.weather79.navigation.WeatherAppScreens
import com.example.weather79.ui.theme.BottomOrange
import com.example.weather79.ui.theme.TopYellow
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(navController: NavController? = null) {
    
    LaunchedEffect(key1 = true, block = {
        delay(2000L)
        navController?.popBackStack()
        navController?.navigate(WeatherAppScreens.MainScreen.name+"/surat")
    })
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(TopYellow, BottomOrange))),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(125.dp),
                painter = painterResource(id = R.drawable.weather),
                contentDescription = "Weather logos"
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Weather79",
                color = Color.DarkGray,
                fontSize = 30.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WeatherSplashScreenPreview() {
    WeatherSplashScreen()
}