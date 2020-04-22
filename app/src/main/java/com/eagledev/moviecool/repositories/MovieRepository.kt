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

/**
 * Handle the data for the movies
 */
class MovieRepository @Inject constructor(private val movieDb4Api: MovieDb4Api, private val movieDao: MovieDao, private val appSharedPreferences: AppSharedPreferences) {

    private lateinit var movieBoundaryCallback: MovieBoundaryCallback
    private lateinit var movieFavoriteBoundaryCallback: MovieFavoriteBoundaryCallback
    private lateinit var movieRatedBoundaryCallback: MovieRatedBoundaryCallback

    /**
     * Get a list of movie recommendations
     * @param coroutineScope The scope to bind [viewModelScope] with the request
     */
     fun getRecommendedMovies(coroutineScope: CoroutineScope): LiveData<PagedList<Movie>>{

        val data = movieDao.getRecommendedMovies()

        movieBoundaryCallback = MovieBoundaryCallback(coroutineScope,movieDb4Api, movieDao, appSharedPreferences )

         return LivePagedListBuilder(data, 1)
             .setBoundaryCallback(movieBoundaryCallback)
             .build()
    }

    /**
     * Get your favorites movies
     * @param coroutineScope The scope to bind [viewModelScope] with the request
     */
    fun getFavoritesMovies(coroutineScope: CoroutineScope): LiveData<PagedList<Movie>>{

        val data = movieDao.getFavoritesMovies()

        movieFavoriteBoundaryCallback = MovieFavoriteBoundaryCallback(coroutineScope,movieDb4Api, movieDao, appSharedPreferences )

        return LivePagedListBuilder(data, 1)
            .setBoundaryCallback(movieFavoriteBoundaryCallback)
            .build()
    }

    /**
     * Get the movies that you rate
     * @param coroutineScope The scope to bind [viewModelScope] with the request
     */
    fun getRatedMovies(coroutineScope: CoroutineScope): LiveData<PagedList<Movie>>{

        val data = movieDao.getRatedMovies()

        movieRatedBoundaryCallback = MovieRatedBoundaryCallback(coroutineScope,movieDb4Api, movieDao, appSharedPreferences )

        return LivePagedListBuilder(data, 1)
            .setBoundaryCallback(movieRatedBoundaryCallback)
            .build()
    }
}