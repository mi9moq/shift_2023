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
    private var currentPage = 1
    private var hasNextPage = true
    private var list = listOf<AnimeItem>()
    override suspend fun findAnimeByTitle(title: String): List<AnimeItem> {
        val nextPage = hasNextPage
        val page = currentPage
        if (list.isNotEmpty() && !nextPage) return list
        val response = animeApi.findAnimeByTitle(title,page)
        currentPage++
        hasNextPage = response.hasNextPage
        val responseAnimeList =
            response.animeList.map { animeItemMapper.mapAnimeItemDtoToEntity(it) }
        list = responseAnimeList
        return list
    }
}

//override suspend fun findAnimeByTitle(title: String): List<AnimeItem> =
//    animeApi.findAnimeByTitle(title).animeList.map {
//        animeItemMapper.mapAnimeItemDtoToEntity(it)
//    }