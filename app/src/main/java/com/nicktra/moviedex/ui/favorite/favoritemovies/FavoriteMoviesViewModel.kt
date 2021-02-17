package com.nicktra.moviedex.ui.favorite.favoritemovies

import androidx.lifecycle.ViewModel
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase

class FavoriteMoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie()
}