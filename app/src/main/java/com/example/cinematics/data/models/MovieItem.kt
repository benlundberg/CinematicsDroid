package com.example.cinematics.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cinematics.data.configs.ServiceConfig

@Entity
data class MovieItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val vote: Double,
    val tagline: String? = "",
    var isFavourite: Boolean = false
) {

    // Posters
    val poster45: String get() = ServiceConfig.PosterPath + "w45/" + posterPath
    val poster92: String get() = ServiceConfig.PosterPath + "w92/" + posterPath
    val poster154: String get() = ServiceConfig.PosterPath + "w154/" + posterPath
    val poster185: String get() = ServiceConfig.PosterPath + "w185/" + posterPath
    val poster632: String get() = ServiceConfig.PosterPath + "h632/" + posterPath
    val poster342: String get() = ServiceConfig.PosterPath + "w342/" + posterPath
    val poster300: String get() = ServiceConfig.PosterPath + "w300/" + posterPath
    val poster1280: String get() = ServiceConfig.PosterPath + "w1280/" + posterPath
    val posterOriginal: String get() = ServiceConfig.PosterPath + "original/" + posterPath
//    val backdrop: String get() = ServiceConfig.PosterPath + resultDto.backdropPath
}