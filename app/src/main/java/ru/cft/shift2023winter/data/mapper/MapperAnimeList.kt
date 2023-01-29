package ru.cft.shift2023winter.data.mapper

import ru.cft.shift2023winter.data.network.AnimeItemDto
import ru.cft.shift2023winter.domain.entity.AnimeItem
import javax.inject.Inject

class MapperAnimeList @Inject constructor() {
    private fun mapAnimeItemDtoToEntity(animeItemDto: AnimeItemDto) = AnimeItem(
        id = animeItemDto.id,
        title = animeItemDto.title,
        image = animeItemDto.image
    )

    fun mapPopularAnimeListToListEntity(list: List<AnimeItemDto>) = list.map {
        mapAnimeItemDtoToEntity(it)
    }
}