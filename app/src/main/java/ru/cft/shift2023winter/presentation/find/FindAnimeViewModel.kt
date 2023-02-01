package ru.cft.shift2023winter.presentation.find

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.FindAnimeByTitleUseCase
import javax.inject.Inject

class FindAnimeViewModel @Inject constructor(
    private val findAnimeByTitleUseCase: FindAnimeByTitleUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<FindAnimeUiState> =
        MutableStateFlow(FindAnimeUiState.Initial)
    val state: StateFlow<FindAnimeUiState> = _state.asStateFlow()

    fun findAnimeByTitle(title: String) {
        viewModelScope.launch {
            _state.value = FindAnimeUiState.Loading
            try {
                val animeList = findAnimeByTitleUseCase(title)
                _state.value = FindAnimeUiState.Content(animeList)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = FindAnimeUiState.Error(e.message)
            }
        }
    }
}