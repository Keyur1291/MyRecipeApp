package com.example.recipeapp

sealed class Screen(val route: String) {

    data object RecipeScreen: Screen("RecipeScreen")
    data object DetailScreen: Screen("DetailScreen")

}