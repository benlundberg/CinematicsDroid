package com.example.cinematics.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.cinematics.R
import com.example.cinematics.ui.common.EnterFadeAnimation
import com.example.cinematics.ui.favourites.Favourites
import com.example.cinematics.ui.favourites.FavouritesViewModel
import com.example.cinematics.ui.movies.Movies
import com.example.cinematics.ui.movies.MoviesViewModel
import com.example.cinematics.ui.search.Search
import com.example.cinematics.ui.search.SearchViewModel

@Composable
fun Home(
    vm: HomeViewModel,
    selectedMovie: (Int) -> Unit
) {
    val selectedTab = CinematicsTab.getTabFromResource(vm.selectedTab.value)
    val tabs = CinematicsTab.values()

    val navController = rememberNavController()

    val searchViewModel: SearchViewModel = hiltNavGraphViewModel()
    val favouritesViewModel: FavouritesViewModel = hiltNavGraphViewModel()

    Scaffold(
        topBar = {
            val title = stringResource(id = R.string.app_name)
            TopAppBar(title = { Text(title) })
        },
        bottomBar = {
            BottomNavigation {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = { Icon(tab.icon, stringResource(id = tab.title)) },
                        label = { Text(text = stringResource(id = tab.title)) },
                        selected = selectedTab == tab,
                        onClick = {

                            if (selectedTab != tab) {
                                vm.selectTab(tab.title)

                                navController.navigate(tab.route) {
                                    popUpTo = navController.graph.startDestination
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = CinematicsTab.MOVIES.route
            ) {
                composable(CinematicsTab.MOVIES.route) {
                    EnterFadeAnimation {
                        val moviesViewModel: MoviesViewModel = hiltNavGraphViewModel(it)
                        Movies(moviesViewModel, movieSelected = selectedMovie)
                    }
                }
                composable(CinematicsTab.SEARCH.route) {
                    EnterFadeAnimation {
                        Search(searchViewModel, movieSelected = selectedMovie)
                    }
                }
                composable(CinematicsTab.FAVOURITES.route) {
                    EnterFadeAnimation {
                        Favourites(favouritesViewModel, movieSelected = selectedMovie)
                    }
                }
            }
        }
    }
}

enum class CinematicsTab(
    @StringRes val title: Int,
    val route: String,
    val icon: ImageVector
) {
    MOVIES(R.string.movies, "movies", Icons.Filled.Movie),
    SEARCH(R.string.search, "search", Icons.Filled.Search),
    FAVOURITES(R.string.favourites, "favourites", Icons.Filled.Favorite);

    companion object {
        fun getTabFromResource(@StringRes resource: Int): CinematicsTab {
            return when (resource) {
                R.string.movies -> MOVIES
                R.string.search -> SEARCH
                R.string.favourites -> FAVOURITES
                else -> MOVIES
            }
        }
    }
}