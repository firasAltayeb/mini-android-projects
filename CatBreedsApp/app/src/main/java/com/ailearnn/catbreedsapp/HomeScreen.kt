package com.ailearnn.catbreedsapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ailearnn.catbreedsapp.data.Breed

@Composable
fun HomeScreen(
    breedsList: List<Breed>,
    navigateToDetail: (Breed) -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            text = "Cat Breeds",
            fontSize = 32.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )


        LazyColumn() {
            items(breedsList) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navigateToDetail(it)
                        },
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colors.surface,
                    elevation = 4.dp,
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondary)
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = it.name,
                        color = MaterialTheme.colors.primary,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}