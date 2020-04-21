package com.eagledev.moviecool.network

import com.eagledev.moviecool.model.AccessTokenRequest
import com.eagledev.moviecool.model.AccessTokenResponse
import com.eagledev.moviecool.model.AuthRequest
import com.eagledev.moviecool.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MovieDb4Api {

    @Headers("content-type: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODVlZmI1YzM5NzE0NGUxYzkwOTMyNTU3YzNiYzk2MiIsInN1YiI6IjVlOWIyOWE2MzEwMzI1MDAxYWM3MWM2NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.a8beztK8gUk-dmRnCZPOd1zadUL2mbJW1MU_SewG4_M")
    @POST("auth/request_token")
    suspend fun requestToken(@Body authRequest: AuthRequest): Response<AuthResponse>


    @Headers("content-type: application/json", "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODVlZmI1YzM5NzE0NGUxYzkwOTMyNTU3YzNiYzk2MiIsInN1YiI6IjVlOWIyOWE2MzEwMzI1MDAxYWM3MWM2NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.a8beztK8gUk-dmRnCZPOd1zadUL2mbJW1MU_SewG4_M")
    @POST("auth/access_token")
    suspend fun requestAccessToken(@Body accessTokenRequest: AccessTokenRequest): Response<AccessTokenResponse>


}