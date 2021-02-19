package com.nicktra.moviedex.favorite.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicktra.moviedex.favorite.R
import com.nicktra.moviedex.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            loadKoinModules(favoriteModule)
            //Setup ViewPager
            val favoritePagerAdapter =
                    FavoritePagerAdapter(this, childFragmentManager)
            favorite_view_pager.adapter = favoritePagerAdapter
            favorite_tabs.setupWithViewPager(favorite_view_pager)
        }
    }
}