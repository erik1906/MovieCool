package com.eagledev.moviecool.ui

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagledev.moviecool.repositories.MovieActionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private  val movieActionsRepository: MovieActionsRepository) : ViewModel() {

    val favoriteResponse = Transformations.map(movieActionsRepository.favoriteResponse){it}

    fun setFavorite(movieId: Int, isFavorite: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            movieActionsRepository.setFavorite(movieId, isFavorite)
        }
    }
}
