package com.nicktra.moviedex.ui.shows

import androidx.lifecycle.ViewModel
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase

class ShowsViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val show = movieUseCase.getAllShows()
}