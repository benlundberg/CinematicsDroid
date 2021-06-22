package com.example.cinematics.ui.movies

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinematics.data.models.MovieItem
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Composable
fun Movies(
    viewModel: MoviesViewModel,
    movieSelected: (Int) -> Unit
) {

    val viewState by viewModel.state.collectAsState()

    if (viewState.loading) LoadingMovies() else MoviesScreenContent(viewState, movieSelected)
}

@Preview(showBackground = true)
@Composable
fun LoadingMovies() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.LightGray,
        targetValue = Color.Gray,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column (modifier = Modifier ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .height(200.dp)
                .fillMaxWidth()
                .background(color)
        )

        Row(
            modifier = Modifier
                .padding(16.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .width(156.dp)
                    .height(200.dp)
                    .background(color)
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .width(156.dp)
                    .height(200.dp)
                    .background(color)
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .width(156.dp)
                    .height(200.dp)
                    .background(color)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(16.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .width(156.dp)
                    .height(200.dp)
                    .background(color)
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .width(156.dp)
                    .height(200.dp)
                    .background(color)
            )
            Spacer(Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .width(156.dp)
                    .height(200.dp)
                    .background(color)
            )
        }
    }
}

@Composable
fun MoviesScreenContent(
    viewState: MoviesViewState,
    movieSelected: (Int) -> Unit
) {
    LazyColumn {
        if (viewState.discoverMovies.isNotEmpty()) {
            item { MovieCarousel(viewState.discoverMovies, movieSelected) }
        }

        if (viewState.nowPLayingMovies.isNotEmpty()) {
            item { MoviesHorizontalList("Now playing", viewState.nowPLayingMovies, movieSelected) }
        }

        if (viewState.upcomingMovies.isNotEmpty()) {
            item { MoviesHorizontalList("Upcoming", viewState.upcomingMovies, movieSelected) }
        }

        if (viewState.nowPLayingMovies.isNotEmpty()) {
            item { MoviesHorizontalList("Top", viewState.topMovies, movieSelected) }
        }

        item { Spacer(Modifier.padding(16.dp)) }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieCarousel(movies: List<MovieItem>, movieSelected: (Int) -> Unit) {
    val pageState = rememberPagerState(pageCount = 6)

    Column {
        HorizontalPager(
            state = pageState
        ) { page ->
            val item = movies[page]
            MovieCarouselItem(movie = item, movieSelected)
        }

        HorizontalPagerIndicator(
            pagerState = pageState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}

@Composable
fun MovieCarouselItem(movie: MovieItem, movieSelected: (Int) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                movieSelected(movie.id)
            },
    ) {
        Card(
            Modifier
                .height(200.dp)
                .fillMaxWidth(),
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
                    text = movie.title!!,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun MoviesHorizontalList(title: String, movies: List<MovieItem>, movieSelected: (Int) -> Unit) {
    Column(
    ) {
        Text(text = title, modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Spacer(modifier = Modifier.width(4.dp)) }

            items(movies) { movie ->
                MovieHorizontalListItem(movie = movie, movieSelected)
            }

            item { Spacer(modifier = Modifier.width(4.dp)) }
        }
    }
}

@Composable
fun MovieHorizontalListItem(movie: MovieItem, movieSelected: (Int) -> Unit) {
    Card(
        Modifier
            .width(156.dp)
            .height(200.dp)
            .fillMaxWidth()
            .clickable { movieSelected(movie.id) },
        shape = RoundedCornerShape(8.dp),
    ) {
        CoilImage(
            data = movie.poster342,
            contentDescription = movie.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        ) {
        }
    }
}