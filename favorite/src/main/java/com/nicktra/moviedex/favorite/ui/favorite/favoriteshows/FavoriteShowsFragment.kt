package com.nicktra.moviedex.favorite.ui.favorite.favoriteshows

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicktra.moviedex.core.ui.MovieAdapter
import com.nicktra.moviedex.favorite.databinding.FavoriteShowsFragmentBinding
import com.nicktra.moviedex.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.favorite_shows_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteShowsFragment : Fragment() {

    private val favoriteShowsViewModel: FavoriteShowsViewModel by viewModel()

    private var _binding: FavoriteShowsFragmentBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FavoriteShowsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
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

            favoriteShowsViewModel.favoriteShow.observe(viewLifecycleOwner, { dataShow ->
                showAdapter.setData(dataShow)
                binding?.root?.view_empty?.visibility = if (dataShow.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding?.rvFavoriteShow) {
                val orientation = this@FavoriteShowsFragment.resources.configuration.orientation
                val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3

                this?.layoutManager = GridLayoutManager(context, spanCount)
                this?.setHasFixedSize(true)
                this?.adapter = showAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.rvFavoriteShow?.adapter = null
        _binding = null
    }
}