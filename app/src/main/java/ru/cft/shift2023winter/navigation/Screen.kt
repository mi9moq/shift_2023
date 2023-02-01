package ru.cft.shift2023winter.navigation

sealed class Screen(
    val route: String
) {
    object Home : Screen(ROUTE_HOME)

    object BestAnime : Screen(ROUTE_BEST_ANIME)

    object Find : Screen(ROUTE_FIND)

    object Favourite : Screen(ROUTE_FAVOURITE)

    object Details : Screen(ROUTE_DETAILS)

    companion object {
        const val ROUTE_BEST_ANIME = "best_anime"
        const val ROUTE_FIND = "find"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_DETAILS = "details"
        const val ROUTE_HOME = "home"
    }
}
