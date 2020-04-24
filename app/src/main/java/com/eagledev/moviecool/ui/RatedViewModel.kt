package com.eagledev.moviecool.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagledev.moviecool.data.repositories.MovieRepository
import javax.inject.Inject

class RatedViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val empty = MutableLiveData<String>()

    private val movies = Transformations.map(empty){movieRepository.getRatedMovies(viewModelScope)}

    val movieList = Transformations.switchMap(movies){
        it
    }

    fun getMoviesRated(){
        empty.value = ""
    }
}
