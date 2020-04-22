package com.eagledev.moviecool.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagledev.moviecool.repositories.AuthenticationRepository
import com.eagledev.moviecool.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val empty = MutableLiveData<String>()

    private val movies = Transformations.map(empty){movieRepository.getFavoritesMovies(viewModelScope)}

    val movieList = Transformations.switchMap(movies){
        it
    }

    fun getMoviesFavorites(){
        empty.value = ""
    }
}
