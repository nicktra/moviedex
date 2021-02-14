package com.nicktra.moviedex.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nicktra.moviedex.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities where isTvShow = 0")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isTvShow = 1")
    fun getAllShows(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isTvShow = 0 and isFavorite = 1")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isTvShow = 1 and isFavorite = 1")
    fun getFavoriteShow(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}