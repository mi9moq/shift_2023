package ru.cft.shift2023winter.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.cft.shift2023winter.data.network.AnimeApi
import ru.cft.shift2023winter.data.repository.AnimeRepositoryImpl
import ru.cft.shift2023winter.domain.repository.AnimeRepository

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindAnimeRepository(impl: AnimeRepositoryImpl): AnimeRepository

    companion object {
        private const val BASE_URL = "https://api.consumet.org/anime/gogoanime/"

        @ApplicationScope
        @Provides
        fun provideAnimeApi(retrofit: Retrofit): AnimeApi {
            return retrofit.create(AnimeApi::class.java)
        }

        @ApplicationScope
        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}