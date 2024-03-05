package com.rustam.myapplication.domain.use_case

import com.rustam.myapplication.data.dto.SearchResultDto
import com.rustam.myapplication.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {
    suspend operator fun invoke(search: String): Flow<SearchResultDto> = flow {
        emit(repository.getSearchResult(search))
    }
}