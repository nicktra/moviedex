package com.nicktra.moviedex.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicktra.moviedex.R
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            //Setup ViewPager
            val favoritePagerAdapter =
                    FavoritePagerAdapter(this, childFragmentManager)
            favorite_view_pager.adapter = favoritePagerAdapter
            favorite_tabs.setupWithViewPager(favorite_view_pager)
        }
    }
}