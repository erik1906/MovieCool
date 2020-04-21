package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class SessionV3Response(
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("session_id")
    val sessionId: String = ""
)