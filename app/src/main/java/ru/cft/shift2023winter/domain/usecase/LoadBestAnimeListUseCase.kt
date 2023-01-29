package ru.cft.shift2023winter.domain.usecase

import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class LoadBestAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke() = repository.loadBestAnimeList()
}