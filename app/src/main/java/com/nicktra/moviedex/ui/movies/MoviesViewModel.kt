package com.nicktra.moviedex.ui.movies

import androidx.lifecycle.ViewModel
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase

class MoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovies()
}