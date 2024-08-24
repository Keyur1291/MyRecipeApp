package com.example.myrecipeapp

sealed class Screen(val route: String) {

    object RecipeScreen: Screen("RecipeScreen")
    object DetailScreen: Screen("DetailScreen")

}