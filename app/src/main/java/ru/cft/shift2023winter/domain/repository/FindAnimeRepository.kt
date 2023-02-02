package ru.cft.shift2023winter.domain.repository

import ru.cft.shift2023winter.domain.entity.AnimeItem

interface FindAnimeRepository {

    suspend fun findAnimeByTitle(title: String): List<AnimeItem>
}