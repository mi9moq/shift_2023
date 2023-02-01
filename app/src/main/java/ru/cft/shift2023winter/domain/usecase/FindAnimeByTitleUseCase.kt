package ru.cft.shift2023winter.domain.usecase

import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.FindAnimeRepository
import javax.inject.Inject

class FindAnimeByTitleUseCase @Inject constructor(
    private val repository: FindAnimeRepository
) {
    suspend operator fun invoke(title: String): List<AnimeItem> =
        repository.findAnimeByTitle(title)
}