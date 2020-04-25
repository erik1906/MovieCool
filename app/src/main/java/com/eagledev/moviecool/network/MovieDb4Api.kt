package com.eagledev.moviecool.network

import com.eagledev.moviecool.model.*
import retrofit2.Response
import retrofit2.http.*

interface MovieDb4Api {

    @POST("auth/request_token")
    suspend fun requestToken(@Body authRequest: AuthRequest,  @Header("Authorization") accessToken: String): Response<AuthResponse>

    @POST("auth/access_token")
    suspend fun requestAccessToken(@Body accessTokenRequest: AccessTokenRequest, @Header("Authorization") accessToken: String): Response<AccessTokenResponse>

    @GET("account/{account_id}/movie/recommendations")
    suspend fun getRecommendations(@Path("account_id") accountId: String, @Header("Authorization") accessToken: String, @Query("page") page: Int): Response<ListResponse>

    @GET("account/{account_id}/movie/favorites")
    suspend fun getFavorites(@Path("account_id") accountId: String, @Header("Authorization") accessToken: String, @Query("page") page: Int): Response<ListResponse>

    @GET("account/{account_id}/movie/rated")
    suspend fun getRated(@Path("account_id") accountId: String, @Header("Authorization") accessToken: String, @Query("page") page: Int): Response<ListResponse>

    
}