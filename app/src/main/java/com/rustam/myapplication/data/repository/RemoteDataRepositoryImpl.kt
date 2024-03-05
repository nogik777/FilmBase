package com.rustam.myapplication.data.repository

import com.rustam.myapplication.data.FilmApi
import com.rustam.myapplication.data.dto.FilmDataDto
import com.rustam.myapplication.data.dto.SearchResultDto
import com.rustam.myapplication.domain.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val api: FilmApi
) : RemoteDataRepository {
    override suspend fun getFilmData(title: String): FilmDataDto {
        return withContext(Dispatchers.Default) {
            api.getFilmData(title = title)
        }
    }

    override suspend fun getSearchResult(search: String): SearchResultDto {
        return withContext(Dispatchers.Default) {
            api.getSearchResult(search = search)
        }
    }
}