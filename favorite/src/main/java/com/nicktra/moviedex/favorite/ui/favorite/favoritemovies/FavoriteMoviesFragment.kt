package com.nicktra.moviedex.favorite.ui.favorite.favoritemovies

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicktra.moviedex.core.ui.MovieAdapter
import com.nicktra.moviedex.favorite.databinding.FavoriteMoviesFragmentBinding
import com.nicktra.moviedex.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.favorite_movies_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    private val favoriteMoviesViewModel: FavoriteMoviesViewModel by viewModel()

    private var _binding: FavoriteMoviesFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FavoriteMoviesFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteMoviesViewModel.favoriteMovie.observe(viewLifecycleOwner, { dataMovie ->
                movieAdapter.setData(dataMovie)
                binding?.root?.view_empty?.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding?.rvFavoriteMovie) {
                val orientation = this@FavoriteMoviesFragment.resources.configuration.orientation
                val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3

                this?.layoutManager = GridLayoutManager(context, spanCount)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}