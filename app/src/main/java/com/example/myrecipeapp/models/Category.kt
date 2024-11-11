package com.example.myrecipeapp.models

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
    val meals: List<Meal>
)

data class CategoriesResponse(val categories: List<Category>)
