package ru.cft.shift2023winter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.cft.shift2023winter.domain.entity.AnimeItem

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToDetail(animeItem: AnimeItem){
        navHostController.navigate(Screen.Details.getRouteWithArgs(animeItem))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState{
    return remember {
        NavigationState(navHostController)
    }
}