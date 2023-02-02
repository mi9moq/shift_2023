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
    private var prevTitle = ""
    override suspend fun findAnimeByTitle(title: String): List<AnimeItem> {
        val nextPage = hasNextPage

        val nextTitle = prevTitle
        if (list.isNotEmpty() && !nextPage) return list
        if(nextTitle != title ){
            currentPage = 1
            prevTitle = title
        }else{
            currentPage++
        }
        val page = currentPage
        val response = animeApi.findAnimeByTitle(title,page)
        hasNextPage = response.hasNextPage
        val responseAnimeList =
            response.animeList.map { animeItemMapper.mapAnimeItemDtoToEntity(it) }
        list = responseAnimeList
        return list
    }
}