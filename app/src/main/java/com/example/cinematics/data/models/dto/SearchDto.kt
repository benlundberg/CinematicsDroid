package com.example.cinematics.data.models.dto

import com.google.gson.annotations.SerializedName

data class RootSearchResultDto (

    @SerializedName("page") val page : Int,
    @SerializedName("results") val results : List<SearchResultDto>,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int
)

data class SearchResultDto (

    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("id") val id : Int,
    @SerializedName("overview") val overview : String,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("vote_average") val vote_average : Double,
    @SerializedName("media_type") val media_type : String,
    @SerializedName("first_air_date") val first_air_date : String,
    @SerializedName("origin_country") val origin_country : List<String>,
    @SerializedName("genre_ids") val genre_ids : List<String>,
    @SerializedName("original_language") val original_language : String,
    @SerializedName("vote_count") val vote_count : Int,
    @SerializedName("name") val name : String,
    @SerializedName("title") val title : String,
    @SerializedName("original_name") val original_name : String
)