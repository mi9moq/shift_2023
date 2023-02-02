package ru.cft.shift2023winter.ui.find

import ru.cft.shift2023winter.domain.entity.AnimeItem

sealed interface FindAnimeUiState{

    object Initial: FindAnimeUiState

    object Loading: FindAnimeUiState

    data class Content(val animeList: List<AnimeItem>): FindAnimeUiState

    data class Error(val message: String?): FindAnimeUiState
}
