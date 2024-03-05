package com.rustam.myapplication.presentation.detail_screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocalMovies
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.rustam.myapplication.presentation.MainViewModel
import com.rustam.myapplication.presentation.detail_screen.components.ErrorScreen
import com.rustam.myapplication.presentation.detail_screen.components.FilmData
import com.rustam.myapplication.presentation.detail_screen.components.LoadingScreen
import com.rustam.myapplication.util.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    title: String,
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(
        key1 = true,
        block = {
            viewModel.getFilmData(title)
        }
    )

    val filmDataResponse =
        viewModel.filmDataResponse.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Film Database",
                    fontFamily = FontFamily.Serif,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.LocalMovies,
                            contentDescription = null
                        )
                    }
                })
        }
    ) { paddingValues ->
        AnimatedContent(
            targetState = filmDataResponse,
            label = "animated_content",
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        300,
                        easing = LinearEasing
                    )
                ) togetherWith fadeOut(
                    animationSpec = tween(
                        300,
                        easing = LinearEasing
                    )
                )
            }
        ) {response ->
            when (response) {
                is Result.Success -> {
                    val filmData = response.data
                    FilmData(filmData = filmData)
                }
                is Result.Loading -> {
                    LoadingScreen()
                }
                is Result.Error -> {
                    val errorMsg = response.error.localizedMessage ?: "Something went wrong"
                    ErrorScreen(filmTitle = title, errorMsg = errorMsg)
                }
                is Result.Idle -> Unit

            }

        }
    }
}