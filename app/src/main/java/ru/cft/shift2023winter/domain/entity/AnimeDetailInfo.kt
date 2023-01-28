package ru.cft.shift2023winter.domain.entity


data class AnimeDetailInfo (
    val title: String,
    val type: String,
    val releasedDate: String,
    val status: String,
    val genres: List<String>,
    val synopsis: String,
    val imageUrl: String,
    val totalEpisodes: String,
)