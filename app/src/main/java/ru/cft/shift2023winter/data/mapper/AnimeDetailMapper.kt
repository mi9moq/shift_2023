package ru.cft.shift2023winter.data.mapper

import ru.cft.shift2023winter.data.network.model.AnimeDetailInfoDto
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import javax.inject.Inject

class AnimeDetailMapper @Inject constructor() {
    fun mapAnimeDetailInfoDtoToEntity(anime: AnimeDetailInfoDto) = AnimeDetailInfo(
        title = anime.title,
        image = anime.image,
        releasedDate = anime.releasedDate,
        description = anime.description,
        genres = anime.genres,
        type = anime.type,
        status = anime.status,
        totalEpisodes = anime.totalEpisodes
    )
}