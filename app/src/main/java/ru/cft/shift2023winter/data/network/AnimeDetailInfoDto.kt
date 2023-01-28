package ru.cft.shift2023winter.data.network

import com.google.gson.annotations.SerializedName


data class AnimeDetailInfoDto (
    @SerializedName("animeTitle")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("releasedDate")
    val releasedDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("animeImg")
    val imageUrl: String,
    @SerializedName("totalEpisodes")
    val totalEpisodes: String
)