package com.rustam.myapplication.presentation.detail_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rustam.myapplication.domain.model.FilmData
import com.rustam.myapplication.util.MySpacer

@Composable
fun FilmData(
    filmData: FilmData
) {
    Column(
       modifier = Modifier
           .fillMaxSize()
           .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(filmData.poster)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(.5f)
            )
        }
        MySpacer(size = 12.dp)
        FilmDataItem(
            title = "Title",
            description = filmData.title
        )
        FilmDataItem(
            title = "Actors",
            description = filmData.actors
        )
        FilmDataItem(
            title = "Awards",
            description = filmData.awards
        )
        FilmDataItem(
            title = "Box Office",
            description = filmData.boxOffice
        )
        FilmDataItem(
            title = "Country",
            description = filmData.country
        )
        FilmDataItem(
            title = "Director",
            description = filmData.director
        )
        FilmDataItem(
            title = "Genre",
            description = filmData.genre
        )
        FilmDataItem(
            title = "Language",
            description = filmData.language
        )
        FilmDataItem(
            title = "Production",
            description = filmData.production
        )
        FilmDataItem(
            title = "Rated",
            description = filmData.rated
        )
        FilmDataItem(
            title = "Released",
            description = filmData.released
        )
        FilmDataItem(
            title = "Runtime",
            description = filmData.runtime
        )
        FilmDataItem(
            title = "Writer",
            description = filmData.writer
        )
        FilmDataItem(
            title = "Year",
            description = filmData.year
        )
        FilmDataItem(
            title = "IMBD Rating",
            description = filmData.imdbRating
        )
        FilmRatings(ratings = filmData.ratings)
        FilmDataItem(
            title = "Plot",
            description = filmData.plot
        )
    }
}