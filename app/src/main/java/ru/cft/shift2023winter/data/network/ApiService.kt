package ru.cft.shift2023winter.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    suspend fun loadPopularAnimeList(): List<AnimeItemDto>

    @GET("popular")
    suspend fun loadPopularAnimeList(@Query(QUERY_PARAM_PAGE) page: Int): List<AnimeItemDto>

    @GET("anime-details/{$PATH_PARAM_ANIME_ID}")
    suspend fun loadAnimeDetails(@Path(PATH_PARAM_ANIME_ID) animeId: String): AnimeDetailInfoDto

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
        private const val PATH_PARAM_ANIME_ID = "animeId"
    }
}