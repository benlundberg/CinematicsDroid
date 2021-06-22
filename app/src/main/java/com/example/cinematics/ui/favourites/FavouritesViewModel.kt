package com.example.cinematics.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinematics.data.dao.MoviesDao
import com.example.cinematics.data.models.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    moviesDao: MoviesDao
) : ViewModel() {

    private val _state = MutableStateFlow(FavouritesViewState())
    val state: StateFlow<FavouritesViewState> get() = _state

    init {

        state.value.isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            val movieFlow = flow {  emit(moviesDao.getAll()) }

            movieFlow.collect { movies ->
                _state.value = FavouritesViewState(movies, false)
            }
        }
    }
}

data class FavouritesViewState(
    val favouriteMovies: List<MovieItem> = emptyList(),
    var isLoading: Boolean = false
)