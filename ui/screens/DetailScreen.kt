package com.asudsc.recipeapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asudsc.recipeapplication.model.Recipe
import com.asudsc.recipeapplication.model.recipes
import com.asudsc.recipeapplication.ui.theme.RecipeApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreen(
    modifier: Modifier = Modifier,
    recipe: Recipe,
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = recipe.name, fontWeight = FontWeight.SemiBold)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface,
                ),
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(
                    top = (padding.calculateTopPadding())
                )
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Column(Modifier.padding(20.dp)) {
                Text(
                    text = "How to make ${recipe.name}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = recipe.description, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(15.dp))
                for (index in recipe.steps.indices) {
                    StepItem(stepNumber = index + 1, description = recipe.steps[index])
                }
            }
        }
    }
}

@Composable
fun StepItem(stepNumber: Int, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Text(
            text = "${stepNumber}.",
            modifier = Modifier
                .padding(end = 8.dp)
                .width(24.dp),
            fontWeight = FontWeight.Bold,

            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = description,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RecipeApplicationTheme {
        DetailScreen(
            modifier = Modifier,
            recipe = recipes[0],
            navigateBack = {}
        )
    }
}
