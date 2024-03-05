package com.rustam.myapplication.data.dto


import com.rustam.myapplication.domain.model.SearchResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultDto(
    @Json(name = "Search")
    val movies: List<Film>,
    @Json(name = "totalResults")
    val totalResults: String,
    @Json(name = "Response")
    val response: String
)

fun SearchResultDto.toSearchResult(): SearchResult {
    return SearchResult(films = movies)
}