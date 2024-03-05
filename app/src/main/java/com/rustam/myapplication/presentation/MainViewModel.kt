package com.rustam.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rustam.myapplication.data.dto.toFilmData
import com.rustam.myapplication.data.dto.toSearchResult
import com.rustam.myapplication.domain.model.FilmData
import com.rustam.myapplication.domain.model.SearchResult
import com.rustam.myapplication.domain.use_case.UseCases
import com.rustam.myapplication.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _filmDataResponse =
        MutableStateFlow<Result<FilmData>>(Result.Idle)
    val filmDataResponse =
        _filmDataResponse.asStateFlow()

    private val _searchResponse =
        MutableStateFlow<Result<SearchResult>>(Result.Idle)
    val searchResponse =
        _searchResponse.asStateFlow()

    fun getFilmData(
        title: String
    ) = viewModelScope.launch {
        useCases.getFilmDataUseCase(title)
            .onStart {
                _filmDataResponse.value = Result.Loading
            }.catch {
                _filmDataResponse.value = Result.Error(it)
            }.collect {
                val result = it.toFilmData()
                _filmDataResponse.value = Result.Success(result)
            }
    }

    fun getSearch(
        search: String
    ) = viewModelScope.launch {
        useCases.getSearchResultUseCase(search)
            .onStart {
                _searchResponse.value = Result.Loading
            }.catch {
                _searchResponse.value = Result.Error(it)
            }.collect {
                val result = it.toSearchResult()
                _searchResponse.value = Result.Success(result)
            }
    }

}