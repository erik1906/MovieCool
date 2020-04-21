package com.eagledev.moviecool.di

import com.eagledev.moviecool.ui.HomeActivity
import com.eagledev.moviecool.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {


    /* Add the activity binding
        Example:
        @ContributesAndroidInjector(modules = [FragmentModule::class])
        abstract fun contributeMainActivity(): MainActivity
    */

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}