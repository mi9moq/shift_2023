package ru.cft.shift2023winter.domain.entity


data class AnimeDetailInfo (
    val title: String,
    val image: String,
    val releasedDate: String?,
    val description: String?,
    val genres: List<String>,
    val type: String?,
    val status: String,
    val totalEpisodes: Int,
)