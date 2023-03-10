package ru.cft.shift2023winter.ui.bestanime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.presentation.ErrorMessage
import ru.cft.shift2023winter.presentation.ViewModelFactory
import ru.cft.shift2023winter.presentation.bestanime.AnimeListUiState
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeViewModel

@Composable
fun BestAnimeScreen(
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues,
    onItemClickListener: (AnimeItem) -> Unit
) {
    val viewModel: BestAnimeViewModel = viewModel(
        factory = viewModelFactory
    )
    val screenState = viewModel.state.collectAsState()
    when (val currentState = screenState.value) {
        is AnimeListUiState.Content -> {
            BestAnime(
                animeList = currentState.animeList,
                onItemClickListener = {
                    onItemClickListener(it)
                },
                onLoaded = {
                    viewModel.loadNextData()
                },
                paddingValues = paddingValues,
                nextDataIsLoading = currentState.nextDataIsLoading
            )
        }

        is AnimeListUiState.Error -> {
            ErrorMessage()
        }

        is AnimeListUiState.Initial -> Unit

        is AnimeListUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun BestAnime(
    onLoaded: () -> Unit,
    animeList: List<AnimeItem>,
    nextDataIsLoading: Boolean,
    paddingValues: PaddingValues,
    onItemClickListener: (AnimeItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = animeList,
            key = { it.id }
        ) { anime ->
            AnimeCard(
                animeItem = anime,
                onItemClickListener = {
                    onItemClickListener(anime)
                }
            )
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                SideEffect {
                    onLoaded()
                }
            }
        }
    }
}

@Composable
private fun AnimeCard(
    animeItem: AnimeItem,
    onItemClickListener: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClickListener()
        }) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                model = animeItem.image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = animeItem.title, fontWeight = FontWeight.W500)
        }
    }
}