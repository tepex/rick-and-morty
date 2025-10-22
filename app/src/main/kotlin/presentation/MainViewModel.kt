package ru.work_mate.rick_and_morty.presentation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.work_mate.rick_and_morty.presentation.model.CharacterListItem
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        search()
    }

    fun search(query: String = "") {
        viewModelScope.launch {
            _state.emit(State(search = TextFieldState(query), isLoading = true))
            Timber.d("Start searching \"$query\"...")
            delay(3.seconds)
            setResult()
        }
    }

    private fun setResult() {
        MutableList(20) { i -> CharacterListItem(i, "item ${(i+1)}") }.toList()
            .also { result ->
                Timber.d("result: $result")
                _state.update { it.copy(result = result, isLoading = false) }
            }
    }

    data class State(
        val search: TextFieldState = TextFieldState(),
        val result: List<CharacterListItem> = emptyList(),
        val isLoading: Boolean = false,
        val page: Int = 0,
        val hasPrevious: Boolean = false,
        val hasNext: Boolean = false
    )
}
