package ru.cft.shift2023winter.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.cft.shift2023winter.data.model.AnimeDetailInfoDto
import ru.cft.shift2023winter.data.model.AnimeListResponse

interface AnimeApi {

    @GET("top-airing")
    suspend fun loadPopularAnimeList(@Query("page") page: Int): AnimeListResponse

    @GET("info/{animeId}")
    suspend fun loadAnimeDetails(@Path("animeId") animeId: String): AnimeDetailInfoDto
    @GET("{title}")
    suspend fun findAnimeByTitle(
        @Path("title") title: String,
        @Query("page") page:Int
    ):AnimeListResponse
}