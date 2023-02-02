package ru.cft.shift2023winter.ui.animedetail

import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo

sealed interface AnimeDetailUiState{

    object Initial: AnimeDetailUiState

    object Loading: AnimeDetailUiState

    data class Content(val animeInfo: AnimeDetailInfo): AnimeDetailUiState

    data class Error(val message: String?): AnimeDetailUiState
}
