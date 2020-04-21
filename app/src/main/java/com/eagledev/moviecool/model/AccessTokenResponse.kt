package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("status_code")
    val statusCode: Int? = 0,
    @SerializedName("status_message")
    val statusMessage: String? = "",
    @SerializedName("account_id")
    val accountId: String = ""
)