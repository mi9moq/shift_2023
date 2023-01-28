package ru.cft.shift2023winter.di

import dagger.Module
import dagger.Provides
import ru.cft.shift2023winter.data.network.ApiFactory
import ru.cft.shift2023winter.data.network.ApiService

@Module
interface DataModule {

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}