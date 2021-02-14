package com.nicktra.moviedex.core.domain.repository

import androidx.lifecycle.LiveData
import com.nicktra.moviedex.core.data.Resource
import com.nicktra.moviedex.core.domain.model.Movie

interface IMovieRepository {
    fun getAllMovies(): LiveData<Resource<List<Movie>>>

    fun getAllShows(): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun getFavoriteShow(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}