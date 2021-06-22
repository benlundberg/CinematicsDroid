package com.example.cinematics.data.remotes

import com.example.cinematics.data.configs.ServiceConfig
import com.example.cinematics.data.models.dto.MovieResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsRemoteService {

    @GET(ServiceConfig.MovieDetails)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int) : MovieResultDto
}