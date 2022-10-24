package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding

import com.udacity.shoestore.login.LoginRegisterViewModel

import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var viewModel: LoginRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        viewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)

        navController = (supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController

        viewModel.currentUser.observe(binding.root.findViewTreeLifecycleOwner()!!, Observer { currentUser: List<String> ->
            if ((currentUser.isEmpty()) and (navController.currentDestination?.id != R.id.loginFragment)) {
                viewModel.logout()
                navController.navigate(R.id.loginFragment)
            }
        })

        setupActionBarWithNavController(navController, binding.NavDrawer)
        setupWithNavController(binding.navView, navController)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == R.layout.fragment_shoe_list) {
                binding.NavDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                binding.NavDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        if ((navController.currentDestination?.id == R.id.loginFragment) or (navController.currentDestination?.id == R.id.shoeListFragment)){
            this.finish()
            return false
        } else {
            return NavigationUI.navigateUp(navController, binding.NavDrawer) || super.onSupportNavigateUp()
        }
    }

    override fun onStart() {
        super.onStart()
        val onBackPressedCallback = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (navController.currentDestination?.id == R.id.loginFragment){
                    finish()
                } else {
                    onSupportNavigateUp()
                }
            }
        }
        onBackPressedDispatcher.addCallback(onBackPressedCallback)

    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}