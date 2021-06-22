package com.example.cinematics.utils

import com.example.cinematics.data.dao.MoviesDao
import com.example.cinematics.data.services.MovieDetailsService
import com.example.cinematics.data.services.MoviesService
import com.example.cinematics.data.services.SearchService
import com.example.cinematics.ui.details.MovieDetailsViewModel
import com.example.cinematics.ui.favourites.FavouritesViewModel
import com.example.cinematics.ui.movies.MoviesViewModel
import com.example.cinematics.ui.search.SearchViewModel

interface Factory<T> {
    fun create(): T
}

class MoviesViewModelFactory(
    private val moviesService: MoviesService
) : Factory<MoviesViewModel> {
    override fun create(): MoviesViewModel {
        return MoviesViewModel(moviesService)
    }
}

class SearchViewModelFactory(
    private val searchService: SearchService
) : Factory<SearchViewModel> {
    override fun create(): SearchViewModel {
        return SearchViewModel(searchService)
    }
}

class FavouritesViewModelFactory(
    private val moviesDao: MoviesDao
) : Factory<FavouritesViewModel> {
    override fun create(): FavouritesViewModel {
        return FavouritesViewModel(moviesDao)
    }
}

class MovieDetailsViewModelFactory(
    private val movieDetailsService: MovieDetailsService,
    private val moviesDao: MoviesDao
) : Factory<MovieDetailsViewModel> {
    override fun create(): MovieDetailsViewModel {
        return MovieDetailsViewModel(movieDetailsService, moviesDao)
    }
}
