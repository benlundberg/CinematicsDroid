package com.example.cinematics.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.dao.MoviesDao
import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.data.services.MovieDetailsService
import com.example.cinematics.data.services.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsService: MovieDetailsService,
    private val moviesDao: MoviesDao
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsViewState())
    val state: StateFlow<MovieDetailsViewState> get() = _state

    var isFavourite = mutableStateOf(false)

    fun addToFavourites() {

        if (isFavourite.value) {
            viewModelScope.launch(Dispatchers.IO) {
                moviesDao.deleteMovies(_state.value.movieItem)
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                moviesDao.insertMovie(_state.value.movieItem)
            }
        }
        isFavourite.value = !isFavourite.value
    }

    fun loadMovie(id: Int) {

        state.value.isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            movieDetailsService.getMovieDetails(id).collect {
                isFavourite.value = it.isFavourite
                _state.value = MovieDetailsViewState(it, false)
            }
        }
    }
}

data class MovieDetailsViewState(
    val movieItem: MovieItem = MovieItem(1, "", "", "", "", 0.0, ""),
    var isLoading: Boolean = false
)