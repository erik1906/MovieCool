package com.eagledev.moviecool.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagledev.moviecool.data.repositories.MovieActionsRepository
import com.eagledev.moviecool.data.repositories.MovieV3Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(private val movieActionsRepository: MovieActionsRepository, private val movieV3Repository: MovieV3Repository) : ViewModel() {


    private val empty = MutableLiveData<String>()

    private val movies = Transformations.map(empty){movieV3Repository.getRecommendedMovies(viewModelScope)}

    val favoriteResponse = Transformations.map(movieActionsRepository.favoriteResponse){it}

    fun setFavorite(movieId: Int, isFavorite: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            movieActionsRepository.setFavorite(movieId, isFavorite)
        }
    }
    val movieList = Transformations.switchMap(movies){
        it
    }

    fun getMovies(){
        empty.value = ""
    }

}
