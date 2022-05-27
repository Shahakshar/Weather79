package com.example.weather79.presentation.screens.favorite_screen

import android.widget.ImageButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather79.model.Favorite
import com.example.weather79.navigation.WeatherAppScreens
import com.example.weather79.ui.theme.BottomOrange
import com.example.weather79.ui.theme.TopYellow
import com.example.weather79.widgets.WeatherAppBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    Scaffold(
        topBar =
        {
            WeatherAppBar(
                navController = navController,
                icon = Icons.Default.ArrowBack,
                title = "Favorite Cities",
                isMainScreen = false,
            ) {
                navController.popBackStack()
            }
        }
    ) {

        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = favoriteViewModel.favList.collectAsState().value

                LazyColumn {
                    items(items = list) {
                        CityRow(
                            it,
                            navController = navController,
                            favoriteViewModel = favoriteViewModel
                        )
                    }
                }

            }
        }

    }

}

@Composable
fun CityRow(
    favorite: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherAppScreens.MainScreen.name+"/${favorite.city}")
            },
        shape = CircleShape,
        color = BottomOrange.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(start = 12.dp))
            Surface(
                shape = CircleShape,
                color = TopYellow,
                contentColor = Color.Black
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(4.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            IconButton(
                modifier = Modifier.padding(end = 6.dp),
                onClick = {
                    favoriteViewModel.deleteFavorite(favorite = favorite)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete Icon",
                    tint = Color.Red
                )
            }
        }
    }

}
