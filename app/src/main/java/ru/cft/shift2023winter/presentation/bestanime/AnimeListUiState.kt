package ru.cft.shift2023winter.presentation.bestanime

import ru.cft.shift2023winter.domain.entity.AnimeItem

sealed interface AnimeListUiState{

    object Initial: AnimeListUiState

    object Loading: AnimeListUiState

    data class Content(
        val animeList: MutableList<AnimeItem>,
        val nextDataIsLoading: Boolean
    ): AnimeListUiState

    data class Error(val message: String?): AnimeListUiState
}
