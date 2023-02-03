package ru.cft.shift2023winter.ui.animedetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.presentation.ErrorMessage
import ru.cft.shift2023winter.presentation.animedetail.AnimeDetailUiState
import ru.cft.shift2023winter.presentation.animedetail.AnimeDetailViewModel

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
    paddingValues: PaddingValues
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
                Text(
                    text = animeDetail.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = animeDetail.image,
                    contentDescription = null,
                    modifier = Modifier.height(300.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.genres),
                        fontWeight = FontWeight.W500
                    )
                    Text(text = animeDetail.genres.joinToString(", "))
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (animeDetail.releaseDate != null) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.release_date),
                            fontWeight = FontWeight.W500
                        )
                        Text(text = animeDetail.releaseDate)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                if (animeDetail.totalEpisodes != 0) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.total_episodes),
                            fontWeight = FontWeight.W500
                        )
                        Text(text = animeDetail.totalEpisodes.toString())
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.anime_status),
                        fontWeight = FontWeight.W500
                    )
                    Text(text = animeDetail.status)
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (animeDetail.type != null) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.anime_type),
                            fontWeight = FontWeight.W500
                        )
                        Text(text = animeDetail.type)
                    }
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