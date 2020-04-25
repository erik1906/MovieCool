package com.eagledev.moviecool.network

import com.eagledev.moviecool.model.*
import retrofit2.Response
import retrofit2.http.*

interface MovieDb4Api {

    @POST(Routes.Auth.REQUEST_TOKEN)
    suspend fun requestToken(@Body authRequest: AuthRequest,  @Header("Authorization") accessToken: String): Response<AuthResponse>

    @POST(Routes.Auth.ACCESS_TOKEN)
    suspend fun requestAccessToken(@Body accessTokenRequest: AccessTokenRequest, @Header("Authorization") accessToken: String): Response<AccessTokenResponse>

    @GET(Routes.Account.RECOMMENDATIONS)
    suspend fun getRecommendations(@Path("account_id") accountId: String, @Header("Authorization") accessToken: String, @Query("page") page: Int): Response<ListResponse>

    @GET(Routes.Account.FAVORITES)
    suspend fun getFavorites(@Path("account_id") accountId: String, @Header("Authorization") accessToken: String, @Query("page") page: Int): Response<ListResponse>

    @GET(Routes.Account.RATED)
    suspend fun getRated(@Path("account_id") accountId: String, @Header("Authorization") accessToken: String, @Query("page") page: Int): Response<ListResponse>

    
}