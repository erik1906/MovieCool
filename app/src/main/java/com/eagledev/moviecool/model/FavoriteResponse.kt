package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class FavoriteResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String? = ""
)