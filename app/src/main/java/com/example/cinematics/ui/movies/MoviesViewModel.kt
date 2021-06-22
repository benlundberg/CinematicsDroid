package com.example.cinematics.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.data.services.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesService: MoviesService
) : ViewModel() {
    private val _state = MutableStateFlow(MoviesViewState())
    val state: StateFlow<MoviesViewState> get() = _state

    init {

        state.value.loading = true

        viewModelScope.launch(Dispatchers.IO) {

            val discoverMovies = moviesService.getDiscoverMovies()
            val topMovies = moviesService.getTopMovies()
            val upcomingMovies = moviesService.getUpcomingMovies()
            val nowPLayingMovies = moviesService.getNowPlayingMovies()

            combine(
                discoverMovies,
                topMovies,
                upcomingMovies,
                nowPLayingMovies
            ) { discoverMovies, topMovies, upcomingMovies, nowPLayingMovies ->
                MoviesViewState(
                    discoverMovies = discoverMovies,
                    nowPLayingMovies = nowPLayingMovies,
                    upcomingMovies = upcomingMovies,
                    topMovies = topMovies,
                    loading = false
                )
            }.collect { _state.value = it }
        }
    }
}

data class MoviesViewState(
    val discoverMovies: List<MovieItem> = emptyList(),
    val nowPLayingMovies: List<MovieItem> = emptyList(),
    val upcomingMovies: List<MovieItem> = emptyList(),
    val topMovies: List<MovieItem> = emptyList(),
    var loading: Boolean = false
)