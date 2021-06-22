package com.example.cinematics.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinematics.data.models.MovieItem
import com.google.accompanist.coil.CoilImage

@Composable
fun Search(
    viewModel: SearchViewModel,
    movieSelected: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    SearchScreenContent(viewModel = viewModel, viewState = viewState, movieSelected)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreenContent(
    viewModel: SearchViewModel,
    viewState: SearchViewState,
    movieSelected: (Int) -> Unit
) {

    Column {
        SearchTextField(viewModel = viewModel)

        if (viewModel.isLoading.value) {
            Searching()
        } else if (viewState.searchResult.movies.isNotEmpty()) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 156.dp)
            ) {
                items(viewState.searchResult.movies) { item ->
                    MovieListItem(item, movieSelected)
                }
            }
        }
    }
}

@Composable
fun MovieListItem(item: MovieItem, movieSelected: (Int) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { movieSelected(item.id) },
        shape = RoundedCornerShape(8.dp),
    ) {
        CoilImage(
            data = item.poster342,
            contentDescription = item.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        ) {
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(viewModel: SearchViewModel) {

    val query = viewModel.searchQuery.value
    val focusManager = LocalFocusManager.current

    Row(Modifier.padding(24.dp)) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            value = query,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            label = { Text("Search") }
        )
        Spacer(Modifier.width(12.dp))
        IconButton(
            modifier = Modifier.align(Alignment.CenterVertically),
            onClick = { focusManager.clearFocus().also { viewModel.onSearchClick() } }
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun Searching() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}