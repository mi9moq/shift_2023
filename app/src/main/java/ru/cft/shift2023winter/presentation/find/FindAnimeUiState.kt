package ru.cft.shift2023winter.presentation.find

import ru.cft.shift2023winter.domain.entity.AnimeItem

sealed interface FindAnimeUiState{

    object Initial: FindAnimeUiState

    object Loading: FindAnimeUiState

    data class Content(
        val animeList: List<AnimeItem>,
        val nextDataIsLoading: Boolean = false
        ): FindAnimeUiState

    data class Error(val message: String?): FindAnimeUiState
}
