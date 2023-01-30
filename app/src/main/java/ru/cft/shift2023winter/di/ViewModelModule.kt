package ru.cft.shift2023winter.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.cft.shift2023winter.presentation.AnimeListViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnimeListViewModel::class)
    fun bindAnimeListViewModel(viewModel: AnimeListViewModel): ViewModel
}