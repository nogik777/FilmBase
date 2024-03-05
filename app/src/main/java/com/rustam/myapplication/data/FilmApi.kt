package com.rustam.myapplication.data

import com.rustam.myapplication.data.dto.FilmDataDto
import com.rustam.myapplication.data.dto.SearchResultDto
import com.rustam.myapplication.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FilmApi {

    @GET("/")
    suspend fun getFilmData(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @QueryMap options: Map<String, String> = mapOf("plot" to "full", "type" to "movie"),
        @Query("t") title: String
    ): FilmDataDto

    @GET("/")
    suspend fun getSearchResult(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("type") type: String = "movie",
        @Query("s") search: String
    ): SearchResultDto
}