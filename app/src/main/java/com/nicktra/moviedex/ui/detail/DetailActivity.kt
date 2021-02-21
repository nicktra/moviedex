package com.nicktra.moviedex.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.nicktra.moviedex.R
import com.nicktra.moviedex.core.domain.model.Movie
import com.nicktra.moviedex.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            binding.tvDataTitle.text = detailMovie.title
            binding.tvDataRelease.text = detailMovie.releaseDate
            binding.tvDataRating.text = detailMovie.voteAverage.toString()
            binding.tvDataPopularity.text = detailMovie.popularity.toString()
            binding.tvDataOverview.text = detailMovie.overview
            Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + detailMovie.backdropPath)
                    .into(binding.backdropDetail)
            Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500" + detailMovie.posterPath)
                    .into(binding.ivPosterDetail)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

            binding.btnShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    if(detailMovie.isTvShow) {
                        putExtra(Intent.EXTRA_TEXT, "Show Title: ${detailMovie.title}\nRelease Date: ${detailMovie.releaseDate}\n\nFind more about ${detailMovie.title} on MovieDex Apps")
                    } else {
                        putExtra(Intent.EXTRA_TEXT, "Movie Title: ${detailMovie.title}\nRelease Date: ${detailMovie.releaseDate}\n\nFind more about ${detailMovie.title} on MovieDex Apps")
                    }
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Share to ")
                startActivity(shareIntent)
            }

            binding.backButton.setOnClickListener { onBackPressed() }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_fill))
        } else {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }
}