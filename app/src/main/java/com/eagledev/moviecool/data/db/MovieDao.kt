package com.eagledev.moviecool.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.eagledev.moviecool.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertMovie(movieList: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getRecommendedMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE favorite == 1")
    fun getFavoritesMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE rated == 1")
    fun getRatedMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie WHERE title like :filter")
    fun searchMovie(filter: String): DataSource.Factory<Int, Movie>

    @Query("UPDATE movie SET favorite = :isFavorite WHERE id == :movieId")
    fun updateMovie(movieId: Int, isFavorite: Boolean)
}