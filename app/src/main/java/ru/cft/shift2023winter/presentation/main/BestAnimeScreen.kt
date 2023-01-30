package ru.cft.shift2023winter.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.presentation.AnimeListUiState
import ru.cft.shift2023winter.presentation.AnimeListViewModel

@Composable
fun BestAnimeScreen(
    viewModel: AnimeListViewModel
){
    val screenState = viewModel.state.collectAsState()
    when(val currentState = screenState.value){
        is AnimeListUiState.Content -> {
            BestAnime(animeList = currentState.animeList)
        }
        is AnimeListUiState.Error -> Text(text = "Error")
        AnimeListUiState.Initial -> Text(text = "Initial")
        AnimeListUiState.Loading -> Text(text = "loading")
    }
}

@Composable
fun BestAnime(
    animeList: List<AnimeItem>
){
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        items(
            items = animeList,
            key= {it.id }
        ){anime->
            Anime(anime)
        }
    }
}

@Composable
private fun Anime(
    animeItem: AnimeItem
){
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                model = animeItem.image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = animeItem.title)
        }
    }
}