package com.eagledev.moviecool.di

import com.eagledev.moviecool.ui.LogInFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLogInFragment(): LogInFragment


}
