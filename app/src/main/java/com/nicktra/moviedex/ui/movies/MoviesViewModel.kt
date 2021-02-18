package com.nicktra.moviedex.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase

class MoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovies().asLiveData()
}