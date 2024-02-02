package com.ailearnn.catbreedsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ailearnn.catbreedsapp.DetailsScreen
import com.ailearnn.catbreedsapp.HomeScreen
import com.ailearnn.catbreedsapp.Routes.DETAILS_SCREEN
import com.ailearnn.catbreedsapp.Routes.HOME_SCREEN
import com.ailearnn.catbreedsapp.data.Breed
import com.ailearnn.catbreedsapp.ui.theme.CatBreedsAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: BreedsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = BreedsViewModel()

        setContent {
            // Setup to observe ViewModel's LiveData & update  composable state as it's changes
            var breedsData: List<Breed> by remember { mutableStateOf(emptyList()) }
            viewModel.breedsLiveData.observe(this) {
                breedsData = it.data ?: emptyList()
            }

            // Trigger GET Breeds from the API asynchronously
            viewModel.getBreeds()

            CatBreedsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = HOME_SCREEN
                    ) {
                        composable(
                            route = HOME_SCREEN,
                        ) {
                            HomeScreen(breedsList = breedsData) { breed ->
                                navController.navigate("$DETAILS_SCREEN/${breed.id}")
                            }
                        }
                        composable(route = "$DETAILS_SCREEN/{breedId}") {
                            val breedId = it.arguments?.getString("breedId") ?: breedsData[0].id
                            DetailsScreen(breedsList = breedsData, breedId)
                        }
                    }
                }
            }
        }
    }
}

