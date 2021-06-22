package com.example.cinematics.data.dao

import androidx.room.*
import com.example.cinematics.data.models.MovieItem

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieItem")
    suspend fun getAll(): List<MovieItem>

    @Query("SELECT * FROM MovieItem WHERE id = :id")
    suspend fun loadMovieById(id: Int): MovieItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movieItems: MovieItem)

    @Delete
    suspend fun deleteMovies(vararg movieItems: MovieItem)
}