package ru.cft.shift2023winter.data.network



data class AnimeDetailInfoDto (
    val title: String,
    val image: String,
    val releasedDate: String?,
    val description: String?,
    val genres: List<String>,
    val type: String?,
    val status: String,
    val totalEpisodes: Int,
)