package com.example.weather79.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weather79.model.Favorite
import com.example.weather79.presentation.screens.favorite_screen.FavoriteViewModel

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showIt = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    val showMenu = remember {
        mutableStateOf(false)
    }

    if(showIt.value)
        ShowPositiveToast(context = context, showIt = showIt.value)

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                }
                IconButton(onClick = {
                    showMenu.value = !showMenu.value
                }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "more icon")
                }
                ShowSettingDropDownMenu(navController = navController, showMenu = showMenu)
            } else {
                Box {}
            }

        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    }
                )
            }
            if (isMainScreen) {
                val listData = title.split(",")
                /**
                 * checking that city is in database or not
                 * if it is in database then return false
                 * if it is not in the list return true
                */
                val isCityInListed = viewModel.favList.collectAsState().value.filter { item ->
                    (item.city == listData[0])
                }.isNullOrEmpty()
                Icon(
                    imageVector = if (!isCityInListed) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = Color.Red.copy(alpha = 0.7f),
                    modifier = Modifier
                        .padding(6.dp)
                        .scale(0.9f)
                        .clickable {

                            /**
                             * here is simple logic of saving data in Database
                             * and
                             * delete it from Database
                             * */

                            if (isCityInListed) {
                                viewModel
                                    .insertFavorite(
                                        Favorite(
                                            city = listData[0],
                                            country = listData[1]
                                        )
                                    ).run {
                                        showIt.value = true
                                    }
                            } else {
                                viewModel
                                    .deleteFavorite(
                                        Favorite(
                                            city = listData[0],
                                            country = listData[1]
                                        )
                                    ).run {
                                        showIt.value = false
                                    }
                            }
                        }
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun ShowPositiveToast(context: Context, showIt: Boolean) {
    if(showIt) {
        Toast.makeText(context, "City Saved in Favorites.", Toast.LENGTH_SHORT).show()
    }
}
