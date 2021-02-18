package com.nicktra.moviedex.core.data.source.local

import com.nicktra.moviedex.core.data.source.local.entity.MovieEntity
import com.nicktra.moviedex.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getAllShows(): Flow<List<MovieEntity>> = movieDao.getAllShows()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun getFavoriteShow(): Flow<List<MovieEntity>> = movieDao.getFavoriteShow()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}