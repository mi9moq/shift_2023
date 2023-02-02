package ru.cft.shift2023winter.ui.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.cft.shift2023winter.navigation.AppNavGraph
import ru.cft.shift2023winter.navigation.rememberNavigationState
import ru.cft.shift2023winter.presentation.ViewModelFactory
import ru.cft.shift2023winter.ui.animedetail.AnimeDetailScreen
import ru.cft.shift2023winter.presentation.animedetail.AnimeDetailViewModel
import ru.cft.shift2023winter.ui.bestanime.BestAnimeScreen
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeViewModel
import ru.cft.shift2023winter.ui.favourite.FavouriteScreen
import ru.cft.shift2023winter.ui.find.FindAnimeScreen

@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory
) {
    val navigationState = rememberNavigationState()
    val animeDetailViewModel: AnimeDetailViewModel = viewModel(
        factory = viewModelFactory
    )
    val bestAnimeViewModel: BestAnimeViewModel = viewModel(
        factory = viewModelFactory
    )

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry =
                    navigationState.navHostController.currentBackStackEntryAsState()
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
                            if (!selected) {
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
                    viewModel = bestAnimeViewModel,
                    paddingValues = paddingValues,
                    onItemClickListener = {
                        navigationState.navigateToDetail(it)
                        animeDetailViewModel.loadAnimeDetail(it.id)
                    }
                )
            },
            findScreenContent = {
                FindAnimeScreen(viewModelFactory = viewModelFactory)
            },
            favouriteScreenContent = {
                FavouriteScreen()
            },
            animeDetailScreenContent = { _ ->
                AnimeDetailScreen(
                    paddingValues = paddingValues,
                    viewModel = animeDetailViewModel
                )
            }
        )
    }
}