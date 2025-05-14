package com.geeksforgeeks.demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit) {
    // Card representing a single movie item
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id) }, // Navigate on click
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Movie poster image
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                modifier = Modifier.height(100.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            // Movie details: title, genre, rating
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}