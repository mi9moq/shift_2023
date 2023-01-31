package ru.cft.shift2023winter.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BestAnimeViewModel::class)
    fun bindAnimeListViewModel(viewModel: BestAnimeViewModel): ViewModel
}