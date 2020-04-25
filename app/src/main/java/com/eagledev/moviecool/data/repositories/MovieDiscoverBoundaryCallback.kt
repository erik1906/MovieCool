package com.eagledev.moviecool.data.repositories

import androidx.paging.PagedList
import com.eagledev.moviecool.R
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.db.MovieDao
import com.eagledev.moviecool.model.Movie
import com.eagledev.moviecool.network.MovieDb3Api
import com.eagledev.moviecool.network.MovieDb4Api
import com.eagledev.moviecool.utils.ResourceProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDiscoverBoundaryCallback constructor(
    private val coroutineScope: CoroutineScope,
    private val movieDb3Api: MovieDb3Api,
    private val movieDao: MovieDao,
    private val resourceProvider: ResourceProvider
): PagedList.BoundaryCallback<Movie>(){

    companion object{
        private const val PAGE_SIZE = 1
    }
    private var isRequestInProgress = false

    private  var currentPage = 1
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        requestData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)
        requestData()
    }

    private fun requestData(){
        if(isRequestInProgress)return

        isRequestInProgress = true

        coroutineScope.launch(Dispatchers.IO) {
            val response = movieDb3Api.getMoviesDiscover(
                resourceProvider.getString(
                    R.string.moviedb_api_3)
            )

            if(response.isSuccessful){
                response.body()?.let{
                    val ratedList = it.movies.map { movie ->
                        movie.favorite = false
                        movie.rated = false
                        movie
                    }
                    movieDao.insertMovies(ratedList)
                    currentPage += PAGE_SIZE
                    isRequestInProgress = false
                }
            }
        }

    }
}