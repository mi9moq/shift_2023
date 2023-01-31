package ru.cft.shift2023winter.presentation.bestanime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.presentation.AnimeListUiState

@Composable
fun BestAnimeScreen(
    viewModel: BestAnimeViewModel
) {
    val screenState = viewModel.state.collectAsState()
    when (val currentState = screenState.value) {
        is AnimeListUiState.Content -> {
            BestAnime(
                animeList = currentState.animeList,
                onItemClickListener = {
                    //TODO()
                },
                viewModel = viewModel,
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
    viewModel: BestAnimeViewModel,
    animeList: List<AnimeItem>,
    nextDataIsLoading: Boolean,
    onItemClickListener: (AnimeItem) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
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
        item{
            if(nextDataIsLoading){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }else{
                SideEffect {
                    viewModel.loadNextData()
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
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                model = animeItem.image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = animeItem.title)
        }
    }
}

@Composable
private fun ErrorMessage(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null
        )
        Text(text = stringResource(R.string.error_message))
    }
}