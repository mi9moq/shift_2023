package ru.cft.shift2023winter.data.repository

import ru.cft.shift2023winter.data.mapper.AnimeItemMapper
import ru.cft.shift2023winter.data.network.AnimeApi
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.FindAnimeRepository
import javax.inject.Inject

class FindAnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi,
    private val animeItemMapper: AnimeItemMapper
) : FindAnimeRepository {
    override suspend fun findAnimeByTitle(title: String): List<AnimeItem> =
        animeApi.findAnimeByTitle(title).animeList.map {
            animeItemMapper.mapAnimeItemDtoToEntity(it)
        }
}