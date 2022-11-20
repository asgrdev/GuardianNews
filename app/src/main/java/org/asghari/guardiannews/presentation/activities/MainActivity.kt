package org.asghari.guardiannews.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

import org.asghari.guardiannews.R


@AndroidEntryPoint
class MainActivity:AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("4>>>>>>>>>>>>",">>>>>>>>>>>>>>>>>>>>>>>>>>>>ss".toString())

        setContentView(R.layout.main_activity)

        val  navHostFragment = supportFragmentManager.findFragmentById(R.id.root_container) as NavHostFragment
        navController  =navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    //set navigation component handle back clicks
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}