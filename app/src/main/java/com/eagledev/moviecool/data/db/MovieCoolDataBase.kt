package com.eagledev.moviecool.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eagledev.moviecool.model.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false)
abstract class MovieCoolDataBase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao
}