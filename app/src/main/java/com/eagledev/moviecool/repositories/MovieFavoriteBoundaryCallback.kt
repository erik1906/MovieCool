package com.eagledev.moviecool.repositories

import androidx.paging.PagedList
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.db.MovieDao
import com.eagledev.moviecool.model.Movie
import com.eagledev.moviecool.network.MovieDb4Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieFavoriteBoundaryCallback constructor(
    private val coroutineScope: CoroutineScope,
    private val movieDb4Api: MovieDb4Api,
    private val movieDao: MovieDao,
    private val appSharedPreferences: AppSharedPreferences): PagedList.BoundaryCallback<Movie>(){

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
            val response = movieDb4Api.getFavorites(
                accountId = appSharedPreferences.getSharedPreferences("accountId"),
                accessToken = "Bearer ${appSharedPreferences.getSharedPreferences("accessToken")}",
                page = currentPage)

            if(response.isSuccessful){
                response.body()?.let{
                    val favList = it.movies.map { movie ->
                        movie.favorite = true
                        movie
                    }
                    movieDao.insertMovie(favList)
                    currentPage += PAGE_SIZE
                    isRequestInProgress = false
                }
            }
        }

    }
}