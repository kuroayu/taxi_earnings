package com.kuro.taxi_earnings.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kuro.taxi_earnings.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.host_fragment)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.topToolbar)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.input_button, R.id.history_button))
        setupWithNavController(bottomNavigation,navController)
        setupWithNavController(toolbar,navController,appBarConfiguration)

    }

}

