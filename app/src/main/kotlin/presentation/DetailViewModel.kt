package ru.work_mate.rick_and_morty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.work_mate.rick_and_morty.presentation.ui.DetailRoute
import kotlin.time.Duration.Companion.seconds

@HiltViewModel(assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel @AssistedInject constructor(
    @Assisted
    val key: DetailRoute
) : ViewModel(), Refreshable {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        refresh()
    }

    override fun refresh() {
        viewModelScope.launch {
            State(isLoading = true).also { _state.emit(it) }
            delay(3.seconds)
            State("Detail screen for id: ${key.id}").also { _state.emit(it) }
        }
    }

    data class State(
        val content: String = "",
        val isLoading: Boolean = false
    )

    @AssistedFactory
    interface Factory {
        fun create(key: DetailRoute): DetailViewModel
    }
}
