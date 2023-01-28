package ru.cft.shift2023winter.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem

interface AnimeRepository {

    fun loadBestAnimeList(): Flow<List<AnimeItem>>

    fun loadAnimeDetail(title: String): Flow<AnimeDetailInfo>
}