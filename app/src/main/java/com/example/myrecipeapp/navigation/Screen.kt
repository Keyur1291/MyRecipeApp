package com.example.myrecipeapp.navigation

import kotlinx.serialization.Serializable

@Serializable
data object RecipeScreen

@Serializable
data class DetailScreen(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

@Serializable
data class MealDetailScreen(
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strTags: String,
    val strYoutube: String,
)