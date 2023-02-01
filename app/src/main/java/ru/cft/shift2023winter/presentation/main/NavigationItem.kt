package ru.cft.shift2023winter.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
){
    object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.navigation_item_main,
        icon = Icons.Filled.Home
    )

    object Favorite : NavigationItem(
        screen = Screen.Favourite,
        titleResId = R.string.navigation_item_favorite,
        icon = Icons.Filled.Favorite
    )

    object Find : NavigationItem(
        screen = Screen.Find,
        titleResId = R.string.navigation_item_find,
        icon = Icons.Default.Search
    )
}
