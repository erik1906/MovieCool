package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class SessionV3Request(
    @SerializedName("access_token")
    val accessToken: String? = ""
)