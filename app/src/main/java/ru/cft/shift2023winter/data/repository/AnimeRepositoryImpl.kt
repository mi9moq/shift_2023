package ru.cft.shift2023winter.data.repository

import ru.cft.shift2023winter.data.mapper.AnimeDetailMapper
import ru.cft.shift2023winter.data.mapper.AnimeListMapper
import ru.cft.shift2023winter.data.network.AnimeApi
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeListMapper: AnimeListMapper,
    private val animeDetailMapper: AnimeDetailMapper,
    private val animeApi: AnimeApi
) : AnimeRepository {
    override suspend fun loadBestAnimeList(): List<AnimeItem> =
        animeListMapper.mapPopularAnimeListToListEntity(animeApi.loadPopularAnimeList().animeList)

    override suspend fun loadAnimeDetail(animeId: String): AnimeDetailInfo =
        animeDetailMapper.mapAnimeDetailInfoDtoToEntity(animeApi.loadAnimeDetails(animeId))
}
