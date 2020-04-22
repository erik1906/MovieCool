package com.eagledev.moviecool.model


import androidx.room.Entity
import androidx.room.TypeConverters
import com.eagledev.moviecool.data.db.MovieTypeConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@TypeConverters(MovieTypeConverter::class)
@Entity(tableName = "movie",
primaryKeys = ["id"])
data class Movie(
    @SerializedName("vote_count")
    val voteCount: Int? = 0,
    @SerializedName("video")
    val video: Boolean? = false,
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("adult")
    val adult: Boolean? = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = listOf(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @SerializedName("overview")
    val overview: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("favorite")
    var favorite: Boolean? = false,
    @SerializedName("rated")
    var rated: Boolean? = false
): Serializable