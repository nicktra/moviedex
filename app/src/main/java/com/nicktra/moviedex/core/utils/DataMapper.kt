package com.nicktra.moviedex.core.utils

import com.nicktra.moviedex.core.data.source.local.entity.MovieEntity
import com.nicktra.moviedex.core.data.source.remote.response.MovieResponse
import com.nicktra.moviedex.core.data.source.remote.response.ShowResponse
import com.nicktra.moviedex.core.domain.model.Movie

object DataMapper {
    fun mapResponsesMovieToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                isFavorite = false,
                isTvShow = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapResponsesShowToEntities(input: List<ShowResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.name,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.firstAirDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                isFavorite = false,
                isTvShow = true
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                popularity = it.popularity,
                isFavorite = it.isFavorite,
                isTvShow = it.isTvShow
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        popularity = input.popularity,
        isFavorite = input.isFavorite,
        isTvShow = input.isTvShow
    )
}