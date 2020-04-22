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

    @Query("SELECT * FROM movie WHERE favorite == 1")
    abstract fun getFavoritesMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE rated == 1")
    abstract fun getRatedMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE title like :filter")
    abstract fun searchMovie(filter: String): DataSource.Factory<Int, Movie>
}