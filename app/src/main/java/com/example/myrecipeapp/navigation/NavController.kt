package com.example.myrecipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.myrecipeapp.MainViewModel
import com.example.myrecipeapp.views.CategoryDetailScreen
import com.example.myrecipeapp.views.MealDetail
import com.example.myrecipeapp.views.RecipeScreen

@Composable
fun NavController(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel,
) {

    val viewState by viewModel.categoryState

    NavHost(navController = navController, startDestination = RecipeScreen) {

        composable<RecipeScreen> {
            RecipeScreen(
                modifier = modifier,
                viewState = viewState,
                navController = navController
            )
        }

        composable<DetailScreen> {
            val arguments = it.toRoute<DetailScreen>()
            CategoryDetailScreen(
                modifier = Modifier,
                idCategory = arguments.idCategory,
                strCategory = arguments.strCategory,
                strCategoryThumb = arguments.strCategoryThumb,
                strCategoryDescription = arguments.strCategoryDescription,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<MealDetailScreen> {
            val arguments = it.toRoute<MealDetailScreen>()
            MealDetail(
                modifier = modifier,
                navController = navController,
                strMeal = arguments.strMeal,
                viewModel = viewModel
            )
        }
    }

}