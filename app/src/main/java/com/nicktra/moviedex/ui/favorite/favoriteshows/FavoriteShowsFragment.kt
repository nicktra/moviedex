package com.nicktra.moviedex.ui.favorite.favoriteshows

import android.content.Intent
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nicktra.moviedex.R
import com.nicktra.moviedex.core.ui.MovieAdapter
import com.nicktra.moviedex.core.ui.ViewModelFactory
import com.nicktra.moviedex.databinding.FavoriteMoviesFragmentBinding
import com.nicktra.moviedex.databinding.FavoriteShowsFragmentBinding
import com.nicktra.moviedex.ui.detail.DetailActivity
import com.nicktra.moviedex.ui.favorite.favoritemovies.FavoriteMoviesViewModel

class FavoriteShowsFragment : Fragment() {

    private lateinit var favoriteShowsViewModel: FavoriteShowsViewModel

    private var _binding: FavoriteShowsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FavoriteShowsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val showAdapter = MovieAdapter()
            showAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteShowsViewModel = ViewModelProvider(this, factory)[FavoriteShowsViewModel::class.java]

            favoriteShowsViewModel.favoriteShow.observe(viewLifecycleOwner, { dataShow ->
                showAdapter.setData(dataShow)
                binding.viewEmpty.root.visibility = if (dataShow.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvFavoriteShow) {
                val orientation = this@FavoriteShowsFragment.resources.configuration.orientation
                val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3

                layoutManager = GridLayoutManager(context, spanCount)
                setHasFixedSize(true)
                adapter = showAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}