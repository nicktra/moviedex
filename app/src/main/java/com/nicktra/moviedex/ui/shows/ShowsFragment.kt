package com.nicktra.moviedex.ui.shows

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nicktra.moviedex.R
import com.nicktra.moviedex.core.data.Resource
import com.nicktra.moviedex.core.ui.MovieAdapter
import com.nicktra.moviedex.databinding.FragmentShowsBinding
import com.nicktra.moviedex.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment() {

    private val showsViewModel: ShowsViewModel by viewModel()

    private var _binding: FragmentShowsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentShowsBinding.inflate(inflater, container, false)
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

            showsViewModel.show.observe(viewLifecycleOwner, { show ->
                if (show != null) {
                    when (show) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            showAdapter.setData(show.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = show.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvShow) {
                val orientation = this@ShowsFragment.resources.configuration.orientation
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