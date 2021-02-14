package com.nicktra.moviedex.core.di

import android.content.Context
import com.nicktra.moviedex.core.data.MovieRepository
import com.nicktra.moviedex.core.data.source.local.LocalDataSource
import com.nicktra.moviedex.core.data.source.local.room.MovieDatabase
import com.nicktra.moviedex.core.data.source.remote.RemoteDataSource
import com.nicktra.moviedex.core.data.source.remote.network.ApiConfig
import com.nicktra.moviedex.core.domain.repository.IMovieRepository
import com.nicktra.moviedex.core.domain.usecase.MovieInteractor
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase
import com.nicktra.moviedex.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}