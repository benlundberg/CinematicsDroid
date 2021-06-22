package com.example.cinematics.data.remotes

import com.example.cinematics.data.configs.ServiceConfig
import com.example.cinematics.data.models.dto.RootMovieResultDto
import retrofit2.http.GET

interface MovieRemoteService {

    @GET(ServiceConfig.DiscoverMovies)
    suspend fun getDiscoverMovies() : RootMovieResultDto

    @GET(ServiceConfig.TopRatedMovies)
    suspend fun getTopRatedMovies() : RootMovieResultDto

    @GET(ServiceConfig.UpcomingMovies)
    suspend fun getUpcomingMovies() : RootMovieResultDto

    @GET(ServiceConfig.NowPlayingMovies)
    suspend fun getNowPlayingMovies() : RootMovieResultDto
}