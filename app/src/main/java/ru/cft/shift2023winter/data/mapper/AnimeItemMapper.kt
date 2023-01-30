package ru.cft.shift2023winter.data.mapper

import ru.cft.shift2023winter.data.model.AnimeItemDto
import ru.cft.shift2023winter.domain.entity.AnimeItem
import javax.inject.Inject

class AnimeItemMapper @Inject constructor() {
    fun mapAnimeItemDtoToEntity(animeItemDto: AnimeItemDto) = AnimeItem(
        id = animeItemDto.id,
        title = animeItemDto.title,
        image = animeItemDto.image
    )
}