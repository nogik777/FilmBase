package com.rustam.myapplication.di

import com.rustam.myapplication.data.FilmApi
import com.rustam.myapplication.data.repository.RemoteDataRepositoryImpl
import com.rustam.myapplication.domain.repository.RemoteDataRepository
import com.rustam.myapplication.domain.use_case.GetFilmDataUseCase
import com.rustam.myapplication.domain.use_case.GetSearchResultUseCase
import com.rustam.myapplication.domain.use_case.UseCases
import com.rustam.myapplication.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideFilmApi(moshi: Moshi): FilmApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(FilmApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: FilmApi): RemoteDataRepository {
        return RemoteDataRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: RemoteDataRepository): UseCases {
        return UseCases(
            getFilmDataUseCase = GetFilmDataUseCase(repository),
            getSearchResultUseCase = GetSearchResultUseCase(repository)
        )
    }

}