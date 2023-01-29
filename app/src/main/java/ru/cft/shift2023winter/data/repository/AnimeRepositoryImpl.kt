package ru.cft.shift2023winter.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.cft.shift2023winter.data.mapper.MapperAnimeDetail
import ru.cft.shift2023winter.data.mapper.MapperAnimeList
import ru.cft.shift2023winter.data.network.ApiService
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val mapperAnimeList: MapperAnimeList,
    private val mapperAnimeDetail: MapperAnimeDetail,
    private val apiService: ApiService
) : AnimeRepository {
    override fun loadBestAnimeList(): Flow<List<AnimeItem>> = flow {
        val list = apiService.loadPopularAnimeList().animeList
        emit(mapperAnimeList.mapPopularAnimeListToListEntity(list))
    }

    override fun loadAnimeDetail(animeId: String): Flow<AnimeDetailInfo> = flow {
        val animeDetail = apiService.loadAnimeDetails(animeId)
        emit(mapperAnimeDetail.mapAnimeDetailInfoDtoToEntity(animeDetail))
    }
}
