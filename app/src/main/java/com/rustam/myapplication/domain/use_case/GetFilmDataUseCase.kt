package com.rustam.myapplication.domain.use_case

import com.rustam.myapplication.data.dto.FilmDataDto
import com.rustam.myapplication.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmDataUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {
    suspend operator fun invoke(title: String): Flow<FilmDataDto> = flow {
        emit(repository.getFilmData(title))
    }
}