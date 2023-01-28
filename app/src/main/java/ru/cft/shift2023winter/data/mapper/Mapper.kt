package ru.cft.shift2023winter.data.mapper

import ru.cft.shift2023winter.data.network.AnimeDetailInfoDto
import ru.cft.shift2023winter.data.network.AnimeItemDto
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem
import javax.inject.Inject

class Mapper @Inject constructor() {
    fun mapAnimeItemDtoToEntity(animeItemDto: AnimeItemDto) = AnimeItem(
        id = animeItemDto.id,
        title = animeItemDto.title,
        imageUrl = animeItemDto.imageUrl
    )

    fun mapAnimeDetailInfoDtoToEntity(animeDetailInfoDto: AnimeDetailInfoDto) = AnimeDetailInfo(
        title = animeDetailInfoDto.title,
        type = animeDetailInfoDto.type,
        releasedDate = animeDetailInfoDto.releasedDate,
        status = animeDetailInfoDto.status,
        genres = animeDetailInfoDto.genres,
        synopsis = animeDetailInfoDto.synopsis,
        imageUrl = animeDetailInfoDto.imageUrl,
        totalEpisodes = animeDetailInfoDto.totalEpisodes
    )
}