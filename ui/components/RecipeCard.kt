package com.asudsc.recipeapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.asudsc.recipeapplication.model.Recipe

@Composable
fun RecipeCard(
    recipe: Recipe,
    openDetail: (Int) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
    ) {
        Column() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Column(Modifier.padding(20.dp)) {
                Text(text = recipe.name, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = recipe.description, fontSize = 15.sp)
                Spacer(modifier = Modifier.height(15.dp))
                ElevatedButton(
                    onClick = { openDetail(recipe.id) },
                ) {
                    Text("Read more")
                }
            }
        }
    }
}