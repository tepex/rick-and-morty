package ru.work_mate.rick_and_morty.presentation.model

import ru.work_mate.rick_and_morty.domain.model.Character

data class CharacterItemUi(
    val id: Character.Id,
    val name: Character.Name
)
