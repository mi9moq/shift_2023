package ru.cft.shift2023winter.data.repository

import ru.cft.shift2023winter.data.mapper.AnimeDetailMapper
import ru.cft.shift2023winter.data.mapper.AnimeItemMapper
import ru.cft.shift2023winter.data.network.AnimeApi
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeItemMapper: AnimeItemMapper,
    private val animeDetailMapper: AnimeDetailMapper,
    private val animeApi: AnimeApi
) : AnimeRepository {
    private var currentPage = 1
    private var hasNextPage = true
    private var list = listOf<AnimeItem>()
    override suspend fun loadBestAnimeList(): List<AnimeItem> {
        val nextPage = hasNextPage
        val page = currentPage
        if (list.isNotEmpty() && !nextPage) return list
        val response = animeApi.loadPopularAnimeList(page)
        currentPage++
        hasNextPage = response.hasNextPage
        val responseAnimeList =
            response.animeList.map { animeItemMapper.mapAnimeItemDtoToEntity(it) }
        list = responseAnimeList
        return list
    }

    override suspend fun loadAnimeDetail(animeId: String): AnimeDetailInfo =
        animeApi.loadAnimeDetails(animeId).let(animeDetailMapper::mapAnimeDetailInfoDtoToEntity)
}
