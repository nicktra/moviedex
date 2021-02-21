package com.nicktra.moviedex.core.data.source.local.room

import androidx.room.*
import com.nicktra.moviedex.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities where isTvShow = 0  ORDER BY popularity DESC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isTvShow = 1  ORDER BY popularity DESC")
    fun getAllShows(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isTvShow = 0 and isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentities where isTvShow = 1 and isFavorite = 1")
    fun getFavoriteShow(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}