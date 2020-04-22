package com.eagledev.moviecool.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eagledev.moviecool.ui.*
import com.eagledev.moviecool.utils.ViewModelFactory
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

   @Binds
   @IntoMap
   @ViewModelKey(RatedViewModel::class)
   abstract fun bindRatedViewModel (viewModelClass: RatedViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(RecommendationsViewModel::class)
   abstract fun bindRecommendationsViewModel (viewModelClass: RecommendationsViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(FavoritesViewModel::class)
   abstract fun bindFavoritesViewModel (viewModelClass: FavoritesViewModel): ViewModel

   @Binds
   @IntoMap
   @ViewModelKey(DetailViewModel::class)
   abstract fun bindDetailViewModel (viewModelClass: DetailViewModel): ViewModel
}