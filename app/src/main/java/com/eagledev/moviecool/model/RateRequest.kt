package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class RateRequest(
    @SerializedName("value")
    val value: Float?
)