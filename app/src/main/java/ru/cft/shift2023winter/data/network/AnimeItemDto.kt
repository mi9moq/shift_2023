package ru.cft.shift2023winter.data.network

import com.google.gson.annotations.SerializedName

data class AnimeItemDto(
    @SerializedName("animeId")
    val id: String,
    @SerializedName("animeTitle")
    val title: String,
    @SerializedName("animeImg")
    val imageUrl: String
)
