package com.example.cinematics

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.cinematics.ui.common.EnterSlideAndFadeAnimation
import com.example.cinematics.ui.details.MovieDetails
import com.example.cinematics.ui.details.MovieDetailsViewModel
import com.example.cinematics.ui.home.Home
import com.example.cinematics.ui.home.HomeViewModel

@Composable
fun MainApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {

        composable(NavScreen.Home.route) {
            val homeViewModel: HomeViewModel = hiltNavGraphViewModel(it)
            Home(homeViewModel) { movieId ->

                // On movie click we navigate to movie details
                navController.navigate("${NavScreen.MovieDetails.route}/$movieId")
            }
        }

        composable(
            NavScreen.MovieDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.MovieDetails.argument0) { type = NavType.IntType }
            )
        ) {

            // Get movie id
            val movieId =
                it.arguments?.getInt(NavScreen.MovieDetails.argument0) ?: return@composable

            // Create view model
            val viewModel: MovieDetailsViewModel = hiltNavGraphViewModel(it)

            EnterSlideAndFadeAnimation {

                // Show composable screen
                MovieDetails(viewModel, movieId) {
                    // On navigating back
                    navController.popBackStack(navController.graph.startDestination, false)
                }
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object MovieDetails : NavScreen("MovieDetails") {

        const val routeWithArgument: String = "MovieDetails/{movieId}"
        const val argument0: String = "movieId"
    }
}