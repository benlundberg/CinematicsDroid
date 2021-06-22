package com.example.cinematics.data.services

import com.example.cinematics.data.mapper.toMovieItem
import com.example.cinematics.data.models.SearchItem
import com.example.cinematics.data.remotes.SearchRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchService @Inject constructor(
    private val searchRemoteService: SearchRemoteService
) {
    suspend fun searchWithQuery(query: String): Flow<SearchItem> {

        val movies = searchRemoteService.search(query).results
            .filter { it.media_type == "movie" }
            .map { it.toMovieItem() }

        return flow {
            emit(SearchItem(movies))
        }
    }
}