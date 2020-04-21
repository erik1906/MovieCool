package com.eagledev.moviecool.model


import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("total_results")
    val totalResults: Int? = 0,
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("results")
    val movies: List<Movie> = listOf()
)