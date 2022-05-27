package com.example.weather79.presentation.screens.about_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather79.R
import com.example.weather79.ui.theme.BottomOrange
import com.example.weather79.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    val annotatedLinkString = buildAnnotatedString {
        val str = "Click here to open link"
        val startIndex = str.indexOf("link")
        val endIndex = startIndex + 4
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.LightGray,
                fontWeight = FontWeight.Normal
            ),
            start = 0,
            end = startIndex - 1
        )
        addStyle(
            style = SpanStyle(
                color = BottomOrange,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "https://openweathermap.org/",
            start = startIndex,
            end = endIndex
        )
    }

    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "About",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                elevation = 4.dp
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.about_app),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(text = stringResource(R.string.api_used),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )
                ClickableText(
                    modifier = Modifier
                        .padding(top = 4.dp),
                    text = annotatedLinkString,
                    onClick = {
                        annotatedLinkString
                            .getStringAnnotations("URL", it, it)
                            .firstOrNull()?.let { stringAnnotation ->
                                uriHandler.openUri(stringAnnotation.item)
                            }
                    }
                )
            }
        }
    }
}