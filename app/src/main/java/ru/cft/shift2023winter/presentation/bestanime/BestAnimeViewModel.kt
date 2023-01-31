package ru.cft.shift2023winter.presentation.bestanime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.LoadBestAnimeListUseCase
import ru.cft.shift2023winter.presentation.AnimeListUiState
import javax.inject.Inject

class BestAnimeViewModel @Inject constructor(
    private val loadBestAnimeListUseCase: LoadBestAnimeListUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<AnimeListUiState> =
        MutableStateFlow(AnimeListUiState.Initial)
    val state: StateFlow<AnimeListUiState> = _state.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _state.value = AnimeListUiState.Loading

            try {
                val animeList = loadBestAnimeListUseCase()
                _state.value = AnimeListUiState.Content(animeList)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = AnimeListUiState.Error(e.message)
            }
        }
    }
}