package com.nicktra.moviedex.ui.favorite.favoriteshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase

class FavoriteShowsViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteShow = movieUseCase.getFavoriteShow().asLiveData()
}