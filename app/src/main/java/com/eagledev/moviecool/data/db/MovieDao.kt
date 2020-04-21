package com.eagledev.moviecool.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eagledev.moviecool.model.Movie

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract  suspend fun insertMovie(movieList: List<Movie>)

    @Query("SELECT * FROM movie")
    abstract fun getRecommendedMovies(): DataSource.Factory<Int, Movie>

}