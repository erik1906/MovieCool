package com.eagledev.moviecool.di

import com.eagledev.moviecool.ui.*
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

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
}
