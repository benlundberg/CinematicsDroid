package com.example.cinematics.data.services

import com.example.cinematics.data.mapper.toMovieItem
import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.data.remotes.MovieRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val movieRemoteService: MovieRemoteService
) {
    suspend fun getDiscoverMovies(): Flow<List<MovieItem>> {
        val movies = movieRemoteService.getDiscoverMovies().results.map { it.toMovieItem() }
        return flow { emit(movies) }
    }

    suspend fun getTopMovies(): Flow<List<MovieItem>> {
        val movies = movieRemoteService.getTopRatedMovies().results.map { it.toMovieItem() }
        return flow { emit(movies) }
    }

    suspend fun getUpcomingMovies(): Flow<List<MovieItem>> {
        val movies = movieRemoteService.getUpcomingMovies().results.map { it.toMovieItem() }
        return flow { emit(movies) }
    }

    suspend fun getNowPlayingMovies(): Flow<List<MovieItem>> {
        val movies = movieRemoteService.getNowPlayingMovies().results.map { it.toMovieItem() }
        return flow { emit(movies) }
    }
}