package com.geeksforgeeks.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the UI content using Compose and apply Material theme
            MaterialTheme {
                // Entry point to navigation
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MovieListScreen(navController: NavController, movieList: List<Movie> = getMovies()) {
    // Column to add padding around the movie list
    Column(modifier = Modifier.padding(12.dp)) {
        // LazyColumn to display a scrollable list of movies
        LazyColumn {
            items(movieList) { movie ->
                // Reusable row component for each movie
                MovieRow(movie = movie) { movieId ->
                    // Navigate to the DetailsScreen when a movie is clicked
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movieId")
                }
            }
        }
    }
}
