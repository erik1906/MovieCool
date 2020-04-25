package com.eagledev.moviecool.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.eagledev.moviecool.MovieCool
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.DefaultAppSharedPreferences
import com.eagledev.moviecool.utils.ResourceProvider

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Defines all the classes that need to be provided in the scope of the app.
 *
 * Define here all objects that are shared throughout the app, like SharedPreferences, navigators or
 * others. If some of those objects are singletons, they should be annotated with `@Singleton`.
 */
@Module
class AppModule {

    @Provides
    fun provideContext(movieCool: MovieCool): Context {
        return movieCool.applicationContext
    }

    @Provides
    @Singleton
    fun provideResourceProvider(application: Application): ResourceProvider{
        return ResourceProvider(application.applicationContext)
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences{
        return application.getSharedPreferences("Deafault", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAppSharedPreferences(sharedPreferences: SharedPreferences) : AppSharedPreferences{
        return DefaultAppSharedPreferences(sharedPreferences)
    }

}