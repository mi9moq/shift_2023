package ru.cft.shift2023winter.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.cft.shift2023winter.data.mapper.Mapper
import ru.cft.shift2023winter.data.network.ApiService
import ru.cft.shift2023winter.domain.entity.AnimeDetailInfo
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val mapper: Mapper,
    private val apiService: ApiService
) : AnimeRepository {
    override fun loadBestAnimeList(): Flow<List<AnimeItem>> = flow {
        val list = apiService.loadPopularAnimeList()
        emit(mapper.mapPopularAnimeListToListEntity(list))
    }

    override fun loadAnimeDetail(title: String): Flow<AnimeDetailInfo> = flow {
        val animeDetail = apiService.loadAnimeDetails(title)
        emit(mapper.mapAnimeDetailInfoDtoToEntity(animeDetail))
    }
}
