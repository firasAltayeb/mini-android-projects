package com.ailearnn.catbreedsapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ailearnn.catbreedsapp.data.Breed
import com.ailearnn.catbreedsapp.ui.MainActivity

@Composable
fun DetailsScreen(
    breedsList: List<Breed>,
    breedId: String
) {
    val breed = breedsList.first(predicate = { it.id == breedId })
//    val breed = breedsList.first { it.id == breedId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = breed.getImageUrl(),
            contentDescription = breed.name,
            modifier = Modifier
                .padding(12.dp)
                .height(250.dp),
            contentScale = ContentScale.Fit,
            onError = { _ ->
                Log.e(
                    MainActivity::class.simpleName,
                    "Async Image failed to load for breed: ${breed.name}"
                )
            },
            error = painterResource(id = R.drawable.ic_image_error)
        )
        Text(text = breed.description)
    }
}