package com.asudsc.recipeapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asudsc.recipeapplication.model.Recipe
import com.asudsc.recipeapplication.model.recipes
import com.asudsc.recipeapplication.ui.components.RecipeCard
import com.asudsc.recipeapplication.ui.components.SearchBar
import com.asudsc.recipeapplication.ui.theme.RecipeApplicationTheme
import com.asudsc.recipeapplication.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier,
    sharedViewModel: SharedViewModel,
    recipeList: List<Recipe>,
    openDetail: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                text = sharedViewModel.searchTextState.value,
                onTextChange = { text ->
                    sharedViewModel.searchTextState.value = text
                },
            )
        },
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = (padding.calculateTopPadding() + 10.dp),
                start = 10.dp,
                end = 10.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            val filteredRecipes = filterRecipes(sharedViewModel.searchTextState.value, recipeList)
            for (recipe in filteredRecipes) {
                item {
                    RecipeCard(
                        recipe = recipe,
                        openDetail = { id ->
                            openDetail(id)
                        },
                    )
                }
            }
        }
    }
}

fun filterRecipes(searchText: String, recipeList: List<Recipe>): List<Recipe> {
    val lowercaseSearchText = searchText.lowercase()
    return recipeList.filter { recipe ->
        recipe.name.lowercase().contains(lowercaseSearchText)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RecipeApplicationTheme {
        HomeScreen(
            modifier = Modifier,
            sharedViewModel = SharedViewModel(),
            recipeList = recipes,
            openDetail = {}
        )
    }
}