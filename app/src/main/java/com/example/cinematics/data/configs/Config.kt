package com.example.cinematics.data.configs

class ServiceConfig() {
    companion object {
        private const val ApiKey = "50a24e67902b9a999d442a7f641a0a5c"

        const val BaseUrl = "https://api.themoviedb.org/3/"

        const val PosterPath = "https://image.tmdb.org/t/p/"
        const val DiscoverMovies = "discover/movie?api_key=$ApiKey"
        const val TopRatedMovies = "movie/top_rated?api_key=$ApiKey"
        const val UpcomingMovies = "movie/upcoming?api_key=$ApiKey"
        const val NowPlayingMovies = "movie/now_playing?api_key=$ApiKey"
        const val Search = "search/multi?api_key=$ApiKey"
        const val MovieDetails = "movie/{movieId}?api_key=$ApiKey"
    }
}
