package ru.cft.shift2023winter.presentation.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.cft.shift2023winter.navigation.AppNavGraph
import ru.cft.shift2023winter.navigation.rememberNavigationState
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeScreen
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeViewModel

@Composable
fun MainScreen(
    viewModel: BestAnimeViewModel
) {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry =
                    navigationState.navHostController.currentBackStackEntryAsState()
                //val currentRoute = navBackStackEntry.value?.destination?.route
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Find,
                    NavigationItem.Favorite
                )
                items.forEach { item ->
                    val selected = navBackStackEntry.value?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if(!selected){
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        }
                    )
                }

            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            animeListScreenContent = {
                BestAnimeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    onItemClickListener = {
                        //TODO
                    }
                )
            },
            findScreenContent = {
                Text(text = "find")
            },
            favouriteScreenContent = {
                Text(text = "favourite")
            },
            animeDetailScreenContent = {animeId->

            }
        )
    }
}