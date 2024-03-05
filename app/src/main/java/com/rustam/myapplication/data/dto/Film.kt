package com.rustam.myapplication.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Film(
    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "imdbID")
    val imdbID: String,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Poster")
    val poster: String
)