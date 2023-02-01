package ru.cft.shift2023winter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    animeListScreenContent: @Composable ()-> Unit,
    animeDetailScreenContent: @Composable (String) ->Unit
){
    navigation(
        startDestination = Screen.BestAnime.route,
        route = Screen.Home.route
    ){
        composable(route = Screen.BestAnime.route){
            animeListScreenContent()
        }
        composable(
            route = Screen.Details.route
        ){
            val animeId = it.arguments?.getString(Screen.KEY_ANIME_ID) ?: ""
            animeDetailScreenContent(animeId)
        }
    }
}