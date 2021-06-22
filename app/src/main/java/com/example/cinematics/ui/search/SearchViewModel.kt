package com.example.cinematics.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.data.models.SearchItem
import com.example.cinematics.data.services.SearchService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchService: SearchService
) : ViewModel() {

    val searchQuery = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    private val _state = MutableStateFlow(SearchViewState())
    val state: StateFlow<SearchViewState> get() = _state

    fun onSearchQueryChange(newSearchQuery: String) {
        searchQuery.value = newSearchQuery
    }

    fun onSearchClick() {

        val query = searchQuery.value

        if (query.isEmpty()) {
            return
        }

        isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {

            searchService.searchWithQuery(query).collect {
                isLoading.value = false
                _state.value = SearchViewState(it)
            }
        }
    }
}

data class SearchViewState(
    var searchResult: SearchItem = SearchItem(emptyList()),
)