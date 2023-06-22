package com.darien.carousels.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darien.carousels.screen.DetailsScreen
import com.darien.carousels.screen.HomeScreen
import com.darien.carousels.utils.AppScreens

@Composable
fun CarouselsNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name
    ) {
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(AppScreens.DetailsScreen.name + "/{id}") { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let {
                DetailsScreen(id = it.toInt())
            }
        }
    }
}