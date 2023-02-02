package ru.cft.shift2023winter.ui.bestanime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.entity.AnimeItem
import ru.cft.shift2023winter.domain.usecase.LoadBestAnimeListUseCase
import javax.inject.Inject

class BestAnimeViewModel @Inject constructor(
    private val loadBestAnimeListUseCase: LoadBestAnimeListUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<AnimeListUiState> =
        MutableStateFlow(AnimeListUiState.Initial)
    val state: StateFlow<AnimeListUiState> = _state.asStateFlow()
    private val animeList = mutableListOf<AnimeItem>()

    init {
        _state.value = AnimeListUiState.Loading
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val newAnimeList = loadBestAnimeListUseCase()
                animeList.addAll(newAnimeList)
                _state.value = AnimeListUiState.Content(animeList)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = AnimeListUiState.Error(e.message)
            }
        }
    }

    fun loadNextData(){
        _state.value = AnimeListUiState.Content(
            animeList,
            nextDataIsLoading = true
        )
        loadData()
    }
}