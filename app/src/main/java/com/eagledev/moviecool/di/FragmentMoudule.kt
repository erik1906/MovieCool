package com.eagledev.moviecool.di

import com.eagledev.moviecool.ui.FavoritesFragment
import com.eagledev.moviecool.ui.LogInFragment
import com.eagledev.moviecool.ui.RatedFragment
import com.eagledev.moviecool.ui.RecommendationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLogInFragment(): LogInFragment

    @ContributesAndroidInjector
    abstract fun contributeRecommendationsFragment(): RecommendationsFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    abstract fun contributeRatedFragment(): RatedFragment

}
