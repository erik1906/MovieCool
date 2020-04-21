package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("redirect_to")
    val redirectTo: String? = ""
)