package com.eagledev.moviecool.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.db.MovieDao
import com.eagledev.moviecool.model.Movie
import com.eagledev.moviecool.network.MovieDb3Api
import com.eagledev.moviecool.network.MovieDb4Api
import com.eagledev.moviecool.utils.ResourceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Handle the movie lists requests
 */
class MovieV3Repository @Inject constructor(private val movieDb3Api: MovieDb3Api, private val movieDao: MovieDao, private val resourceProvider: ResourceProvider) {

    private lateinit var movieDiscoverBoundaryCallback: MovieDiscoverBoundaryCallback


    /**
     * Get a list of movie recommendations
     * @param coroutineScope The scope to bind viewModelScope with the request
     */
     fun getRecommendedMovies(coroutineScope: CoroutineScope): LiveData<PagedList<Movie>>{

        val data = movieDao.getRecommendedMovies()

        movieDiscoverBoundaryCallback = MovieDiscoverBoundaryCallback(coroutineScope,movieDb3Api, movieDao, resourceProvider )

         return LivePagedListBuilder(data, 1)
             .setBoundaryCallback(movieDiscoverBoundaryCallback)
             .build()
    }


}