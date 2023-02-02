package ru.cft.shift2023winter.presentation.bestanime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.LoadBestAnimeListUseCase
import javax.inject.Inject

class BestAnimeViewModel @Inject constructor(
    private val loadBestAnimeListUseCase: LoadBestAnimeListUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<AnimeListUiState> =
        MutableStateFlow(AnimeListUiState.Initial)
    val state: StateFlow<AnimeListUiState> = _state.asStateFlow()

    init {
        _state.value = AnimeListUiState.Loading
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val newAnimeList = loadBestAnimeListUseCase()
                _state.value = AnimeListUiState.Content(
                    animeList = newAnimeList.toMutableList(),
                    nextDataIsLoading = false
                )
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = AnimeListUiState.Error(e.message)
            }
        }
    }

    fun loadNextData(){
        val currentState = (_state.value as? AnimeListUiState.Content) ?: return
        _state.value = currentState.copy(nextDataIsLoading = true)
        viewModelScope.launch {
            try {
                val newAnimeList = loadBestAnimeListUseCase()
                currentState.animeList.addAll(newAnimeList)
                _state.value = currentState.copy(nextDataIsLoading = false)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = AnimeListUiState.Error(e.message)
            }
        }
    }
}