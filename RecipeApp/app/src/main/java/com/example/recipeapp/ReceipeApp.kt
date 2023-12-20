package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipeapp.Routes.DETAIL_SCREEN
import com.example.recipeapp.Routes.RECIPE_SCREEN

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(
        navController = navController,
        startDestination = RECIPE_SCREEN
    ) {
        composable(route = RECIPE_SCREEN) {
            RecipeScreen(
                viewState = viewState,
                navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                    navController.navigate(DETAIL_SCREEN)
                },
            )
        }
        composable(route = DETAIL_SCREEN) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat")
                    ?: Category(
                        "", "", "", ""
                    )
            CategoryDetailScreen(category)
        }
    }
}