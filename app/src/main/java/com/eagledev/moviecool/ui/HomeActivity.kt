package com.eagledev.moviecool.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eagledev.moviecool.R
import com.eagledev.moviecool.di.Injectable
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasAndroidInjector, Injectable {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    override fun androidInjector() = dispatchingAndroidInjector
}
