package com.asudsc.recipeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asudsc.recipeapplication.model.recipes
import com.asudsc.recipeapplication.ui.screens.DetailScreen
import com.asudsc.recipeapplication.ui.screens.HomeScreen
import com.asudsc.recipeapplication.ui.theme.RecipeApplicationTheme
import com.asudsc.recipeapplication.viewmodels.SharedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedViewModel: SharedViewModel by viewModels()
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            RecipeApplicationTheme {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(
                            modifier = Modifier.fillMaxSize(),
                            sharedViewModel = sharedViewModel,
                            recipeList = recipes,
                            openDetail = { id ->
                                navController.navigate("detail/$id")
                            },
                        )
                    }
                    composable(
                        "detail/{recipeId}",
                        arguments = listOf(navArgument("recipeId") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val recipeId = backStackEntry.arguments?.getInt("recipeId")
                        DetailScreen(
                            recipe = recipes[recipeId ?: 0],
                            navigateBack = {
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}