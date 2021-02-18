package com.nicktra.moviedex.core.domain.usecase

import androidx.lifecycle.LiveData
import com.nicktra.moviedex.core.data.Resource
import com.nicktra.moviedex.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getAllShows(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteShow(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}