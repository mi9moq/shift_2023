package ru.cft.shift2023winter.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.cft.shift2023winter.data.network.AnimeApi
import ru.cft.shift2023winter.data.repository.AnimeRepositoryImpl
import ru.cft.shift2023winter.data.repository.FindAnimeRepositoryImpl
import ru.cft.shift2023winter.domain.repository.AnimeRepository
import ru.cft.shift2023winter.domain.repository.FindAnimeRepository

@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun bindAnimeRepository(impl: AnimeRepositoryImpl): AnimeRepository

    @Binds
    @ApplicationScope
    fun bindFindAnimeRepository(impl: FindAnimeRepositoryImpl): FindAnimeRepository

    companion object {
        private const val BASE_URL = "https://api.consumet.org/anime/gogoanime/"

        @ApplicationScope
        @Provides
        fun provideAnimeApi(retrofit: Retrofit): AnimeApi =
            retrofit.create()

        @ApplicationScope
        @Provides
        fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @ApplicationScope
        @Provides
        fun provideHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }
}