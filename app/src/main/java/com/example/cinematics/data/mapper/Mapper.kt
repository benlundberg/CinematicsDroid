package com.example.cinematics.data.mapper

import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.data.models.dto.MovieResultDto
import com.example.cinematics.data.models.dto.SearchResultDto

fun MovieResultDto.toMovieItem(): MovieItem {
    return MovieItem(
        this.id,
        this.title,
        this.poster_path,
        this.overview,
        this.release_date,
        this.vote_average,
        this.tagline,
        isFavourite = false
    )
}

fun SearchResultDto.toMovieItem(): MovieItem {
    return MovieItem(
        this.id,
        this.title,
        this.poster_path,
        "",
        "",
        0.0,
        isFavourite = false
    )
}