package ru.cft.shift2023winter.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cft.shift2023winter.data.network.model.AnimeDetailInfoDto
import ru.cft.shift2023winter.data.network.model.AnimeListResponse

interface AnimeApi {
    @GET("top-airing")
    suspend fun loadPopularAnimeList(): AnimeListResponse

    @GET("top-airing")
    suspend fun loadPopularAnimeList(@Query(QUERY_PARAM_PAGE) page: Int): AnimeListResponse

    @GET("info/{$PATH_PARAM_ANIME_ID}")
    suspend fun loadAnimeDetails(@Path(PATH_PARAM_ANIME_ID) animeId: String): AnimeDetailInfoDto

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
        private const val PATH_PARAM_ANIME_ID = "animeId"
    }
}