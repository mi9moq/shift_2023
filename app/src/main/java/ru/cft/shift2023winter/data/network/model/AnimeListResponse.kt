package ru.cft.shift2023winter.data.network.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    val currentPage: Int,
    val hasNextPage: Boolean,
    @SerializedName("results")
    val animeList: List<AnimeItemDto>
)