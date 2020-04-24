package com.eagledev.moviecool.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagledev.moviecool.data.repositories.MovieRepository
import javax.inject.Inject



class SearchViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val filter = MutableLiveData<String>()

    private val movies = Transformations.map(filter){movieRepository.searchMovie(it)}

    val movieList = Transformations.switchMap(movies){
        it
    }

    fun searchMovie(filterText: String){
        filter.value = filterText
    }
}
