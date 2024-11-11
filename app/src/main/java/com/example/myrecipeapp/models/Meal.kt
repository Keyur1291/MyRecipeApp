package com.example.myrecipeapp.models

data class Meal(
    val idMeal: Int = 0,
    val strMeal: String = "",
    val strMealThumb: String = "",
    val strCategory: String = "",
    val strArea: String = "",
    val strInstructions: String = "",
    val strTags: String = "",
    val strYoutube: String = "",
)

data class MealsResponse(val meals: List<Meal>)
