package com.nicktra.moviedex.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicktra.moviedex.core.di.Injection
import com.nicktra.moviedex.core.domain.usecase.MovieUseCase
import com.nicktra.moviedex.ui.detail.DetailViewModel
import com.nicktra.moviedex.ui.favorite.favoritemovies.FavoriteMoviesViewModel
import com.nicktra.moviedex.ui.favorite.favoriteshows.FavoriteShowsViewModel
import com.nicktra.moviedex.ui.movies.MoviesViewModel
import com.nicktra.moviedex.ui.shows.ShowsViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMovieUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(ShowsViewModel::class.java) -> {
                ShowsViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                FavoriteMoviesViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteShowsViewModel::class.java) -> {
                FavoriteShowsViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}