package com.example.myrecipeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myrecipeapp.MainViewModel
import com.example.myrecipeapp.models.Meal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetail(
    modifier: Modifier = Modifier,
    strMeal: String,
    navController: NavController,
    viewModel: MainViewModel
) {
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
                    Text(text = strMeal)
                }

            )
        }
    ) { innerPaddings ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPaddings.calculateTopPadding())
        ) {
            LazyColumn {
                items(viewModel.fetchMealDetail(strMeal)) { meal ->
                    MealDetailItem(
                        modifier = Modifier,
                        meal = meal
                    )
                }
            }
        }
    }
}


@Composable
fun MealDetailItem(
    modifier: Modifier = Modifier,
    meal: Meal
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            alignment = Alignment.Center,
            painter = rememberAsyncImagePainter(meal.strMealThumb),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(15.dp))
                .width(150.dp)
                .height(150.dp)

        )
        HorizontalDivider()
        ReusableRow(
            title = "Youtube",
            detail = meal.strYoutube
        )
        HorizontalDivider()
        ReusableRow(
            title = "Tags",
            detail = meal.strTags
        )
        HorizontalDivider()
        ReusableRow(
            title = "Area",
            detail = meal.strArea
        )
        HorizontalDivider()
        ReusableRow(
            title = "Category",
            detail = meal.strCategory
        )
        HorizontalDivider()
        ReusableRow(
            title = "Instructions",
            detail = meal.strInstructions
        )

    }
}

@Composable
fun ReusableRow(
    modifier: Modifier = Modifier,
    title: String,
    detail: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            text = title
        )
        Spacer(Modifier.width(4.dp))
        Text(
            modifier = Modifier,
            text = detail
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    MealDetailItem(
        meal = Meal(
            idMeal = 0,
            strMeal = "strMeal",
            strMealThumb = "strMealThumb",
            strCategory = "strCategory",
            strArea = "strArea",
            strInstructions = "strInstructions",
            strTags = "strTags",
            strYoutube = "strYoutube",
        )
    )
}