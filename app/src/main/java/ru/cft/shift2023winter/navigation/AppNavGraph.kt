package ru.cft.shift2023winter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    animeListScreenContent: @Composable () -> Unit,
    findScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
    animeDetailScreenContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            animeListScreenContent = animeListScreenContent,
            animeDetailScreenContent = animeDetailScreenContent
        )
        composable(route = Screen.Find.route) {
            findScreenContent()
        }
        composable(route = Screen.Favourite.route) {
            favouriteScreenContent()
        }
    }
}