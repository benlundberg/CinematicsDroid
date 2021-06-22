package com.example.cinematics.ui.favourites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cinematics.data.models.MovieItem
import com.google.accompanist.coil.CoilImage

@Composable
fun Favourites(
    viewModel: FavouritesViewModel,
    movieSelected: (Int) -> Unit
) {

    val viewState by viewModel.state.collectAsState()

    if (viewState.isLoading) LoadingFavourites() else FavouriteScreenContent(
        viewState,
        movieSelected
    )
}

@Composable
fun FavouriteScreenContent(viewState: FavouritesViewState, movieSelected: (Int) -> Unit) {

    LazyColumn {
        if (viewState.favouriteMovies.isNotEmpty()) {
            items(viewState.favouriteMovies) { movie ->
                FavouriteMovieItem(movie, movieSelected)
            }
        }
    }

}

@Composable
fun FavouriteMovieItem(movie: MovieItem, movieSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { movieSelected(movie.id) }
    ) {
        Card(
            Modifier.fillMaxWidth(0.3f),
            shape = RoundedCornerShape(8.dp),
        ) {
            CoilImage(
                data = movie.poster154,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            ) {
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = movie.title)
            Text(text = movie.overview, maxLines = 4, overflow = TextOverflow.Ellipsis)
        }
    }

}

@Composable
fun LoadingFavourites() {

}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    FavouriteMovieItem(movie = MovieItem(1, "Batman", "", "Lorem ipsum", "", 0.0)) {

    }
}