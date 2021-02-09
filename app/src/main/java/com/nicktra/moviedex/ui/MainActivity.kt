package com.nicktra.moviedex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicktra.moviedex.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Bottom Navigation View.
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        //Pass the ID's of Different destinations
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie, R.id.navigation_show, R.id.navigation_favorite, R.id.navigation_about
            )
        )

        //Initialize NavController.
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}