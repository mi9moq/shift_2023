package ru.cft.shift2023winter.presentation.animedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.domain.usecase.LoadAnimeDetailUseCase
import javax.inject.Inject

class AnimeDetailViewModel @Inject constructor(
    private val loadAnimeDetailUseCase: LoadAnimeDetailUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<AnimeDetailUiState> =
        MutableStateFlow(AnimeDetailUiState.Initial)
    val state: StateFlow<AnimeDetailUiState> = _state.asStateFlow()

    fun loadAnimeDetail(animeId: String) {
        viewModelScope.launch {
            _state.value = AnimeDetailUiState.Loading
            try {
                val animeDetail = loadAnimeDetailUseCase(animeId)
                _state.value = AnimeDetailUiState.Content(animeDetail)
            } catch (rethrow: CancellationException) {
                throw rethrow
            } catch (e: Exception) {
                _state.value = AnimeDetailUiState.Error(e.message)
            }
        }
    }
}