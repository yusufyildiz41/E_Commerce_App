package com.yusufyildiz.ecommerceappnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.MainThread
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.yusufyildiz.ecommerceappnew.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        //Bottom Navigation Bar Implementation

        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navController
        )

        // Action Bar Implementation
        /*
        NavigationUI.setupActionBarWithNavController(this,navController)

         */

        navHostFragment.navController.addOnDestinationChangedListener{_,destination,_ ->

            when(destination.id) {
                R.id.signUpFragment,R.id.signInFragment -> {
                    binding.bottomNav.isGone = true
                }
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}