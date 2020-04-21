package com.eagledev.moviecool.network

import com.eagledev.moviecool.model.SessionV3Request
import com.eagledev.moviecool.model.SessionV3Response
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieDb3Api {


    @POST("authentication/session/convert/4")
    suspend fun requestAccessToken(@Query("api_key") apiKey: String, @Body sessionV3Request: SessionV3Request): Response<SessionV3Response>
}