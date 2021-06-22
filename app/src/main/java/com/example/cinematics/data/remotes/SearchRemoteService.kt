package com.example.cinematics.data.remotes

import com.example.cinematics.data.configs.ServiceConfig
import com.example.cinematics.data.models.dto.RootSearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteService {

    @GET(ServiceConfig.Search)
    suspend fun search(@Query("query") query: String) : RootSearchResultDto
}