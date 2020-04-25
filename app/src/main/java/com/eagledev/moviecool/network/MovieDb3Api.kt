package com.eagledev.moviecool.network

import com.eagledev.moviecool.model.*
import retrofit2.Response
import retrofit2.http.*

interface MovieDb3Api {


    @POST(Routes.AUTH_V3)
    suspend fun requestAccessToken(@Query("api_key") apiKey: String, @Body sessionV3Request: SessionV3Request): Response<SessionV3Response>


    /**
     * Set the movie to the favorites
     */
    @POST(Routes.FAVORITE)
    suspend fun postFavorite(@Path("account_id") accountId: String, @Query("api_key") apiKey: String,
                             @Query("session_id") sessionID: String,
                             @Body favoriteRequest: FavoriteRequest): Response<SimpleResponse>

    @POST(Routes.RATING)
    suspend fun postRate(@Path("movie_id") movieId: String, @Query("api_key") apiKey: String,
                             @Query("session_id") sessionID: String,
                             @Body rateRequest: RateRequest): Response<SimpleResponse>

    @GET(Routes.DISCOVER)
    suspend fun getMoviesDiscover(@Query("api_key") apiKey: String): Response<ListResponse>
}