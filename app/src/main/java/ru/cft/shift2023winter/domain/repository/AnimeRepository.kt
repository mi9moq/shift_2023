package ru.cft.shift2023winter.domain.repository

import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem

interface AnimeRepository {

    suspend fun loadBestAnimeList(): List<AnimeItem>

    suspend fun loadAnimeDetail(animeId: String): AnimeDetailInfo
}