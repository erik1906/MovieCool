package com.eagledev.moviecool.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eagledev.moviecool.R
import com.eagledev.moviecool.data.AppSharedPreferences
import com.eagledev.moviecool.data.Result
import com.eagledev.moviecool.data.db.MovieDao
import com.eagledev.moviecool.model.FavoriteRequest
import com.eagledev.moviecool.model.RateRequest
import com.eagledev.moviecool.network.MovieDb3Api
import com.eagledev.moviecool.network.MovieDb4Api
import com.eagledev.moviecool.utils.Event
import com.eagledev.moviecool.utils.ResourceProvider
import timber.log.Timber
import javax.inject.Inject

/**
 * Handle the movie actions: like and rate
 */

class MovieActionsRepository @Inject constructor(private val movieDb3Api: MovieDb3Api, private val movieDao: MovieDao, private val appSharedPreferences: AppSharedPreferences,
                                                 private val resourceProvider: ResourceProvider){

    private val _favoriteResponse = MutableLiveData<Result<Event<Boolean>>>()

    val favoriteResponse: LiveData<Result<Event<Boolean>>>
        get() = _favoriteResponse

    private val _rateResponse = MutableLiveData<Result<Event<Boolean>>>()

    val rateResponse: LiveData<Result<Event<Boolean>>>
        get() = _rateResponse

    suspend fun setFavorite(movieId: Int, isFavorite: Boolean){

        _favoriteResponse.postValue(Result.Loading)

        val response = movieDb3Api.postFavorite(appSharedPreferences.getSharedPreferences("accountId"), resourceProvider.getString(
            R.string.moviedb_api_3),
            appSharedPreferences.getSharedPreferences("sessionId"),
            FavoriteRequest(mediaId = movieId, favorite = isFavorite)
        )

        if(response.isSuccessful){
            movieDao.updateMovie(movieId, isFavorite)
            _favoriteResponse.postValue(Result.Success(Event(true)))

        }else{
            _favoriteResponse.postValue(Result.Success(Event(false)))
        }
    }

    suspend fun setRating(movieId: Int, rate: Float){

        _rateResponse.postValue(Result.Loading)

        try {
            val response = movieDb3Api.postRate(movieId.toString(), resourceProvider.getString(
                R.string.moviedb_api_3),
                appSharedPreferences.getSharedPreferences("sessionId"),
                RateRequest(rate)
            )

            if(response.isSuccessful){
                movieDao.updateRate(movieId)
                _rateResponse.postValue(Result.Success(Event(true)))

            }else{
                _rateResponse.postValue(Result.Success(Event(false)))
            }
        }catch (e: Exception){
            Timber.e(e)
            _rateResponse.postValue(Result.Error(e))
        }

    }

}