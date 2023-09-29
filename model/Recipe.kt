package com.asudsc.recipeapplication.model

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    var steps: List<String>
)