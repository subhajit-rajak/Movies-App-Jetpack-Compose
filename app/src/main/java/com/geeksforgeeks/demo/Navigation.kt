package com.geeksforgeeks.demo

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    // Define navigation graph
    NavHost(
        navController = navController,
        // Initial screen
        startDestination = MovieScreens.HomeScreen.name
    ) {
        // Home screen route
        composable(MovieScreens.HomeScreen.name) {
            MovieListScreen(navController = navController)
        }

        // Details screen route with movie ID as argument
        composable(
            route = MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                // Get movie ID from route
                movieId = backStackEntry.arguments?.getString("movie")
            )
        }
    }
}