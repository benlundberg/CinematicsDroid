package com.example.cinematics.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cinematics.data.models.MovieItem
import com.example.cinematics.ui.theme.CinematicsTheme
import com.google.accompanist.coil.CoilImage

@Composable
fun MovieDetails(
    viewModel: MovieDetailsViewModel,
    movieId: Int,
    onBack: () -> Unit
) {
    viewModel.loadMovie(movieId)

    val viewState by viewModel.state.collectAsState()

    if (viewState.isLoading) {
        LoadingMovie()
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = viewState.movieItem.title,
                            color = LocalContentColor.current,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back button"
                            )
                        }
                    }
                )
            },
            floatingActionButton = {
                val isFavourite by remember { viewModel.isFavourite }
                val transition = updateTransition(targetState = isFavourite, label = "")
                val color by transition.animateColor(transitionSpec = {
                    when {
                        false isTransitioningTo true ->
                            spring(stiffness = 50f)
                        else ->
                            tween(durationMillis = 500)
                    }
                }, label = "") { state ->
                    when (state) {
                        true -> MaterialTheme.colors.primary
                        false -> Color.White
                    }
                }

                FloatingActionButton(
                    backgroundColor = color,
                    onClick = {
                        viewModel.addToFavourites()
                    }) {
                    Icon(Icons.Filled.Favorite, "")
                }
            }
        ) {
            MovieDetailsContent(viewState.movieItem, viewModel)
        }
    }
}

@Composable
fun MovieDetailsContent(movie: MovieItem, viewModel: MovieDetailsViewModel) {
    Column {
        MovieDetailHeader(movie)
        Text(
            text = movie.overview,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MovieDetailHeader(movie: MovieItem) {
    Surface(
        modifier = Modifier.padding(16.dp),
    ) {
        Card(
            Modifier
                .height(200.dp)
                .fillMaxWidth(1f),
            shape = RoundedCornerShape(8.dp),
        ) {
            CoilImage(
                data = movie.poster342,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            ) {
            }
            Surface(
                color = Color.Black.copy(0.32f)
            ) {
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(16.dp, 24.dp),
            ) {
                Text(
                    text = movie.tagline!!,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun LoadingMovie() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

