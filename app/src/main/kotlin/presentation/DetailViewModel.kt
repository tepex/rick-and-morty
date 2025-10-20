package ru.work_mate.rick_and_morty.presentation

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.work_mate.rick_and_morty.presentation.ui.DetailRoute

@HiltViewModel(assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel @AssistedInject constructor(
    @Assisted
    val key: DetailRoute
) : ViewModel() {

    val content = "Detail screen for id: ${key.id}"

    @AssistedFactory
    interface Factory {
        fun create(key: DetailRoute): DetailViewModel
    }
}
