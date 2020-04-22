package com.eagledev.moviecool.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.eagledev.moviecool.R
import com.eagledev.moviecool.di.Injectable
import com.eagledev.moviecool.utils.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasAndroidInjector, Injectable {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(savedInstanceState == null){
            setUpBottomNavBar()
        }

    }

    private fun setUpBottomNavBar() {

        val navGraphs = listOf(R.navigation.recommended, R.navigation.favorites, R.navigation.rated)

        val controller = (bottom_nav as BottomNavigationView).setupWithNavController(
            navGraphIds = navGraphs,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )
        setSupportActionBar(toolbar)
        controller.observe(this, Observer {
            setupActionBarWithNavController(it)
        })

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
    override fun androidInjector() = dispatchingAndroidInjector
}
