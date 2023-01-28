package ru.cft.shift2023winter.data.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    fun loadPopularAnimeList(): Flow<List<AnimeItemDto>>

    @GET("popular")
    fun loadPopularAnimeList(@Query(QUERY_PARAM_PAGE) page: Int): Flow<List<AnimeItemDto>>

    @GET("anime-details/{$PATH_PARAM_ANIME_ID}")
    fun loadAnimeDetails(@Path(PATH_PARAM_ANIME_ID) animeId: String): Flow<AnimeDetailInfoDto>

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
        private const val PATH_PARAM_ANIME_ID = "animeId"
    }
}