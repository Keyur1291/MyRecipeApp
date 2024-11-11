package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipeapp.models.Category
import com.example.myrecipeapp.models.Meal
import com.example.myrecipeapp.models.recipeService
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _categoryState = mutableStateOf(RecipeState())
    val categoryState: State<RecipeState> = _categoryState

    private val _mealState = mutableStateOf(MealState())
    val mealState: State<MealState> = _mealState

    private val _mealDetailState = mutableStateOf(MealState())
    val mealDetailState: State<MealState> = _mealDetailState

    init {
        fetchCategories()
    }

    fun fetchMealDetail(strMeal: String): List<Meal> {

        viewModelScope.launch {
            try {
                val mealResponse = recipeService.getMealDetail(strMeal)
                _mealDetailState.value = mealDetailState.value.copy(
                    list = mealResponse.meals,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _mealDetailState.value = mealDetailState.value.copy(
                    loading = false,
                    error = "Error fetching meals"
                )
            }
        }
        return mealDetailState.value.list
    }

     fun fetchMeals(strCategory: String): List<Meal> {

        viewModelScope.launch {
            try {
                val mealResponse = recipeService.getMeals(strCategory)
                _mealState.value = mealState.value.copy(
                    list = mealResponse.meals,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _mealState.value = mealState.value.copy(
                    loading = false,
                    error = "Error fetching list of meals"
                )
            }
        }
         return mealState.value.list
    }

    private fun fetchCategories() {

        viewModelScope.launch {
            try {
                val catResponse = recipeService.getCategories()
                _categoryState.value = categoryState.value.copy(
                    list = catResponse.categories,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _categoryState.value = categoryState.value.copy(
                    loading = false,
                    error = "Error fetching list of meals"
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
    data class MealState(
        val loading: Boolean = true,
        val list: List<Meal> = emptyList(),
        val error: String? = null
    )

}