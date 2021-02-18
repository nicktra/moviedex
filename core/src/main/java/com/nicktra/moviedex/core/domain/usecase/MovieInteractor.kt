package com.nicktra.moviedex.core.domain.usecase

import com.nicktra.moviedex.core.domain.model.Movie
import com.nicktra.moviedex.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getAllShows() = movieRepository.getAllShows()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun getFavoriteShow() = movieRepository.getFavoriteShow()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}