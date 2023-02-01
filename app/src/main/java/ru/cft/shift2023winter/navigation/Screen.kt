package ru.cft.shift2023winter.navigation

import ru.cft.shift2023winter.domain.entity.AnimeItem

sealed class Screen(
    val route: String
) {
    object Home : Screen(ROUTE_HOME)

    object BestAnime : Screen(ROUTE_BEST_ANIME)

    object Find : Screen(ROUTE_FIND)

    object Favourite : Screen(ROUTE_FAVOURITE)

    object Details : Screen(ROUTE_DETAILS){
        private const val ROUTE_FOR_ARGS = "details"
        fun getRouteWithArgs(animeItem: AnimeItem):String{
            return "$ROUTE_FOR_ARGS/${animeItem.id}"
        }
    }

    companion object {
        const val KEY_ANIME_ID = "anime_id"
        private const val ROUTE_BEST_ANIME = "best_anime"
        private const val ROUTE_FIND = "find"
        private const val ROUTE_FAVOURITE = "favourite"
        private const val ROUTE_DETAILS = "details/{$KEY_ANIME_ID}"
        private const val ROUTE_HOME = "home"
    }
}
