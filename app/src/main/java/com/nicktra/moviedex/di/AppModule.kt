package com.nicktra.moviedex.di

import com.nicktra.moviedex.core.domain.usecase.MovieInteractor
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase
import com.nicktra.moviedex.ui.detail.DetailViewModel
import com.nicktra.moviedex.ui.favorite.favoritemovies.FavoriteMoviesViewModel
import com.nicktra.moviedex.ui.favorite.favoriteshows.FavoriteShowsViewModel
import com.nicktra.moviedex.ui.movies.MoviesViewModel
import com.nicktra.moviedex.ui.shows.ShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { ShowsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteShowsViewModel(get()) }
}