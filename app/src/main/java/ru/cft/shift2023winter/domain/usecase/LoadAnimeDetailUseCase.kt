package ru.cft.shift2023winter.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class LoadAnimeDetailUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    fun loadAimeDetail(title: String): Flow<AnimeDetailInfo> {
        return repository.loadAnimeDetail(title = title)
    }
}