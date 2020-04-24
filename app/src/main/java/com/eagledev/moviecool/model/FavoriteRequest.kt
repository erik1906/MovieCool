package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("media_type")
    val mediaType: String? = "movie",
    @SerializedName("media_id")
    val mediaId: Int? = 0,
    @SerializedName("favorite")
    val favorite: Boolean? = false
)