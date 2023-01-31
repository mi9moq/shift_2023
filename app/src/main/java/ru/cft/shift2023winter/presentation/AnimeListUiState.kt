package ru.cft.shift2023winter.presentation

import ru.cft.shift2023winter.domain.entity.AnimeItem

sealed interface AnimeListUiState{

    object Initial: AnimeListUiState

    object Loading: AnimeListUiState

    data class Content(
        val animeList: List<AnimeItem>,
        val nextDataIsLoading: Boolean = false
    ): AnimeListUiState

    data class Error(val message: String?): AnimeListUiState
}
