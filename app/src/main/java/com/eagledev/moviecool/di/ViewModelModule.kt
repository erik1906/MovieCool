package com.eagledev.moviecool.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eagledev.moviecool.utils.ViewModelFactory
import com.eagledev.moviecool.ui.LogInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {


   @Binds
   abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

   @Binds
   @IntoMap
   @ViewModelKey(LogInViewModel::class)
   abstract fun bindLogInViewModel (viewModelClass: LogInViewModel): ViewModel
}