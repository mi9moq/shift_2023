package ru.cft.shift2023winter.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.cft.shift2023winter.ui.animedetail.AnimeDetailViewModel
import ru.cft.shift2023winter.ui.bestanime.BestAnimeViewModel
import ru.cft.shift2023winter.ui.find.FindAnimeViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BestAnimeViewModel::class)
    fun bindAnimeListViewModel(viewModel: BestAnimeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimeDetailViewModel::class)
    fun bindAnimeDetailViewModel(viewModel: AnimeDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FindAnimeViewModel::class)
    fun bindFindAnimeViewModel(viewModel: FindAnimeViewModel): ViewModel
}