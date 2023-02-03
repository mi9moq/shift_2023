package ru.cft.shift2023winter.data.model

data class AnimeDetailInfoDto (
    val title: String,
    val image: String,
    val releaseDate: String?,
    val description: String?,
    val genres: List<String>,
    val type: String?,
    val status: String,
    val totalEpisodes: Int,
)