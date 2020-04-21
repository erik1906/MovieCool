package com.eagledev.moviecool.data.db

import androidx.room.TypeConverter
import com.eagledev.moviecool.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MovieTypeConverter {

    private val gson = Gson()

    private inline  fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    @TypeConverter
    @JvmStatic
    fun toMovieList(value: String?): List<Movie>?{
        if(value == null){
            return null
        }
        return gson.fromJson<List<Movie>>(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromMovieList(value: List<Movie>?): String? {
        if(value == null){
            return null
        }
        return gson.toJson(value)
    }

    @TypeConverter
    @JvmStatic
    fun toIntList(value: String?): List<Int>?{
        if(value == null){
            return null
        }
        return gson.fromJson<List<Int>>(value)
    }

    @TypeConverter
    @JvmStatic
    fun fromIntList(value: List<Int>?): String? {
        if(value == null){
            return null
        }
        return gson.toJson(value)
    }
}