package com.example.weather79.presentation.screens.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather79.navigation.WeatherAppScreens
import com.example.weather79.ui.theme.BottomOrange
import com.example.weather79.ui.theme.TopYellow
import com.example.weather79.widgets.WeatherAppBar

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(navController: NavController) {

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Search",
                navController = navController,
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
            ) {
                navController.popBackStack()
                navController.navigate(WeatherAppScreens.MainScreen.name+"/surat")
            }
        }
    ) {
        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)) {
                    // Log.d("TAG", "SearchScreen: $it")
                    navController.popBackStack()
                    navController.navigate(WeatherAppScreens.MainScreen.name+"/$it")
                }
            }
        }

    }

}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "Enter City Name",
            onAction = KeyboardActions {
                if(!valid) {
                    return@KeyboardActions
                }
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyBoardController?.hide()
            })
    }
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        label = { Text(text = placeHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = TopYellow,
            cursorColor = Color.DarkGray,
            focusedLabelColor = BottomOrange,
            unfocusedLabelColor = Color.LightGray,
            unfocusedBorderColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}
