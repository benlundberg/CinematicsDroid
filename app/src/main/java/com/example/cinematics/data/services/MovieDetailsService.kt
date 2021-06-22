package com.example.cinematics.data.services

import com.example.cinematics.data.dao.MoviesDao
import com.example.cinematics.data.mapper.toMovieItem
import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.data.remotes.MovieDetailsRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailsService @Inject constructor(
    private val movieDetailsRemoteService: MovieDetailsRemoteService,
    private val moviesDao: MoviesDao
) {
    suspend fun getMovieDetails(id: Int): Flow<MovieItem> {

        val localMovie = moviesDao.loadMovieById(id)

        if (localMovie != null) {
            localMovie.isFavourite = true
            return flow { emit(localMovie) }
        }

        return flow { emit(movieDetailsRemoteService.getMovieDetails(id).toMovieItem()) }
    }
}