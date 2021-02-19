package com.nicktra.moviedex.favorite.ui.favorite.favoritemovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase

class FavoriteMoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}