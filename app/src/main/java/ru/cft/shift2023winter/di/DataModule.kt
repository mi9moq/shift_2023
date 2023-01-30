package ru.cft.shift2023winter.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.cft.shift2023winter.data.network.ApiFactory
import ru.cft.shift2023winter.data.network.AnimeApi
import ru.cft.shift2023winter.data.repository.AnimeRepositoryImpl
import ru.cft.shift2023winter.domain.repository.AnimeRepository

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindAnimeRepository(impl: AnimeRepositoryImpl): AnimeRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): AnimeApi {
            return ApiFactory.animeApi
        }
    }
}