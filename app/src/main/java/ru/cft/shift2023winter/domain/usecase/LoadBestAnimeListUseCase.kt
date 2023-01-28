package ru.cft.shift2023winter.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class LoadBestAnimeListUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    fun loadBestAnime():Flow<List<AnimeItem>>{
        return repository.loadBestAnimeList()
    }
}