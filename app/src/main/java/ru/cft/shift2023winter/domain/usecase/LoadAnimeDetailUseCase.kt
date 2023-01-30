package ru.cft.shift2023winter.domain.usecase

import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class LoadAnimeDetailUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(animeId: String): AnimeDetailInfo =
        repository.loadAnimeDetail(animeId = animeId)
}