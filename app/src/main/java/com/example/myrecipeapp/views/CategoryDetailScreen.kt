package com.example.myrecipeapp.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.MainViewModel
import com.example.myrecipeapp.navigation.MealDetailScreen
import com.example.myrecipeapp.models.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    modifier: Modifier = Modifier,
    strCategory: String,
    strCategoryThumb: String,
    idCategory: String,
    strCategoryDescription: String,
    viewModel: MainViewModel,
    navController: NavController
) {

    var showFullDesc by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(text = strCategory)
                }

            )
        }
    ) { innerPaddings ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPaddings.calculateTopPadding())
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Image(
                    alignment = Alignment.Center,
                    painter = rememberAsyncImagePainter(strCategoryThumb),
                    contentDescription = "$idCategory thumb",
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .clickable {
                            showFullDesc = !showFullDesc
                        }
                        .padding(8.dp)
                        .animateContentSize(),
                    maxLines = if(showFullDesc) 100 else 2,
                    overflow = TextOverflow.Ellipsis,
                    text = strCategoryDescription,
                    textAlign = TextAlign.Justify,
                )
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    text = "List of meals for this category:"
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp)
            ) {
                items(viewModel.fetchMeals(strCategory)) { meals ->
                    MealItem(
                        meal = meals,
                        navController = navController
                    )
                }
            }
        }
    }

}

@Composable
fun MealItem(
    meal: Meal,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
            .clickable {
                navController.navigate(route = MealDetailScreen(
                    idMeal = meal.idMeal,
                    strMeal = meal.strMeal,
                    strMealThumb = meal.strMealThumb,
                    strCategory = meal.strCategory,
                    strArea = meal.strArea,
                    strInstructions = meal.strInstructions,
                    strTags = meal.strTags,
                    strYoutube = meal.strYoutube
                )
                )
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(Modifier.height(8.dp))
        Text(
            textAlign = TextAlign.Center,
            text = meal.strMeal,
            color = MaterialTheme.colorScheme.onSurface,
            style = TextStyle(fontWeight = FontWeight.SemiBold),
        )
    }
}