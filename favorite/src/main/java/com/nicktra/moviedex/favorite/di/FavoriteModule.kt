package com.nicktra.moviedex.favorite.di

import com.nicktra.moviedex.favorite.ui.favorite.favoritemovies.FavoriteMoviesViewModel
import com.nicktra.moviedex.favorite.ui.favorite.favoriteshows.FavoriteShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMoviesViewModel(get()) }
    viewModel { FavoriteShowsViewModel(get()) }
}