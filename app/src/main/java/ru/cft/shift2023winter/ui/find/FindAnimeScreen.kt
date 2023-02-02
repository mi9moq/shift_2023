package ru.cft.shift2023winter.ui.find

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.presentation.ErrorMessage
import ru.cft.shift2023winter.presentation.ViewModelFactory
import ru.cft.shift2023winter.presentation.find.FindAnimeUiState
import ru.cft.shift2023winter.presentation.find.FindAnimeViewModel

@Composable
fun FindAnimeScreen(
    viewModelFactory: ViewModelFactory
) {
    val viewModel: FindAnimeViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.state.collectAsState()
    Column(
        modifier = Modifier.padding(top = 16.dp, bottom = 40.dp)
    ) {
        TextField(onValueChange = {
            viewModel.findAnimeByTitle(it)
        })
        Spacer(modifier = Modifier.height(16.dp))
        when (val currentState = screenState.value) {
            is FindAnimeUiState.Content -> {
                if (currentState.animeList.isEmpty()) {
                    NothingFind()
                } else {
                    FoundAnime(
                        animeList = currentState.animeList,
                        viewModel = viewModel,
                        nextDataIsLoading = currentState.nextDataIsLoading,
                    )
                }
            }
            is FindAnimeUiState.Error -> {
                ErrorMessage()
            }
            FindAnimeUiState.Initial -> Unit
            FindAnimeUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun NothingFind() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(R.string.nothing_found))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_sad_smile),
            contentDescription = stringResource(R.string.sad_smile)
        )
    }
}

@Composable
private fun TextField(
    onValueChange: (String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        value = text,
        singleLine = true,
        onValueChange = {
            text = it
            onValueChange(it)
        }
    )
}

@Composable
private fun FoundAnime(
    viewModel: FindAnimeViewModel,
    animeList: List<AnimeItem>,
    nextDataIsLoading: Boolean
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
                animeItem = anime
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
                    viewModel.loadNextAnimeByTitle()
                }
            }
        }
    }
}

@Composable
private fun AnimeCard(
    animeItem: AnimeItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
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