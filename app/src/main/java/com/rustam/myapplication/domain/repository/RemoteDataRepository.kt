package com.rustam.myapplication.domain.repository

import com.rustam.myapplication.data.dto.FilmDataDto
import com.rustam.myapplication.data.dto.SearchResultDto

interface RemoteDataRepository {

    suspend fun getFilmData(title: String): FilmDataDto

    suspend fun getSearchResult(search: String): SearchResultDto
}