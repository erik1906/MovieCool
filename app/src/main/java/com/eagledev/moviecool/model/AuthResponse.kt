package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("status_code")
    val statusCode: Int? = 0,
    @SerializedName("status_message")
    val statusMessage: String? = "",
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("request_token")
    val requestToken: String? = ""
)