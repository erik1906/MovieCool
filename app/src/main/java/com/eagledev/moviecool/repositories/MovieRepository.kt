package com.eagledev.moviecool.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.db.MovieDao
import com.eagledev.moviecool.model.Movie
import com.eagledev.moviecool.network.MovieDb4Api
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDb4Api: MovieDb4Api, private val movieDao: MovieDao, private val appSharedPreferences: AppSharedPreferences) {

    private lateinit var movieBoundaryCallback: MovieBoundaryCallback

     fun getRecommendedMovies(coroutineScope: CoroutineScope): LiveData<PagedList<Movie>>{

        val data = movieDao.getRecommendedMovies()

        movieBoundaryCallback = MovieBoundaryCallback(coroutineScope,movieDb4Api, movieDao, appSharedPreferences )

        val pagedList = LivePagedListBuilder(data, 20)
            .setBoundaryCallback(movieBoundaryCallback)
            .build()

         return pagedList
    }
}