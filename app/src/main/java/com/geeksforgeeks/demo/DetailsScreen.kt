package com.geeksforgeeks.demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    // Find the movie based on passed ID
    val movie = getMovies().firstOrNull { it.id == movieId }

    // Show fallback text if movie not found
    if (movie == null) {
        Text("Movie not found")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies", modifier = Modifier.padding(start = 8.dp)) },
                navigationIcon = {
                    // Back navigation icon
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(12.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            // Movie poster and title section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = movie.poster,
                    contentDescription = movie.title,
                    modifier = Modifier.height(200.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }

            // Divider between poster and movie details
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.padding(12.dp))

            // Movie details (text info)
            Column(modifier = Modifier.padding(12.dp, 0.dp)) {
                Text(text = "Year: ${movie.year}", style = MaterialTheme.typography.labelLarge)
                Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.labelLarge)
                Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.labelLarge)
                Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.labelLarge)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.labelLarge)
                Text(text = "Plot: ${movie.plot}", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Section title for images
            Text(
                text = "Movie Images",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(12.dp, 0.dp)
            )

            // Horizontal grid of additional movie images
            HorizontalScrollableImageGrid(listOf(movie))
        }
    }
}

@Composable
fun HorizontalScrollableImageGrid(movies: List<Movie>) {
    val images = movies[0].images

    // Lazy horizontal grid displaying movie images
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(300.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(images.size) { index ->
            val image = images[index]
            Card(
                modifier = Modifier.size(240.dp, 140.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Movie Poster",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}