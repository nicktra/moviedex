package com.nicktra.moviedex.core.data.source.local

import androidx.lifecycle.LiveData
import com.nicktra.moviedex.core.data.source.local.entity.MovieEntity
import com.nicktra.moviedex.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = movieDao.getAllMovies()

    fun getAllShows(): LiveData<List<MovieEntity>> = movieDao.getAllShows()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun getFavoriteShow(): LiveData<List<MovieEntity>> = movieDao.getFavoriteShow()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}