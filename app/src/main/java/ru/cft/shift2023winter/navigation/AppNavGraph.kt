package ru.cft.shift2023winter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    findScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.BestAnime.route
    ) {
        composable(route = Screen.BestAnime.route) {
            homeScreenContent()
        }
        composable(route = Screen.Find.route) {
            findScreenContent()
        }
        composable(route = Screen.Favourite.route) {
            favouriteScreenContent()
        }
    }
}