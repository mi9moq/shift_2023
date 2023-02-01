package ru.cft.shift2023winter.presentation.animedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.presentation.ErrorMessage

@Composable
fun AnimeDetailScreen(
    paddingValues: PaddingValues,
    viewModel: AnimeDetailViewModel
) {
    val screenState = viewModel.state.collectAsState()
    when (val currentState = screenState.value) {
        is AnimeDetailUiState.Content -> {
            AnimeDetail(animeDetail = currentState.animeInfo, paddingValues = paddingValues)
        }
        is AnimeDetailUiState.Error -> {
            ErrorMessage()
        }
        AnimeDetailUiState.Initial -> Unit
        AnimeDetailUiState.Loading -> {
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
private fun AnimeDetail(
    animeDetail: AnimeDetailInfo,
    paddingValues: PaddingValues,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(text = animeDetail.title)
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = animeDetail.image,
                    contentDescription = null,
                    modifier = Modifier.height(300.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (animeDetail.releasedDate != null) {
                    Text(text = "Release Date: ${animeDetail.releasedDate}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (animeDetail.description != null) {
                    Text(text = "Description:", fontWeight = FontWeight.W500)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = animeDetail.description)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}