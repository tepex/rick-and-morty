@file:Suppress("FunctionName")

package ru.work_mate.rick_and_morty.domain

import kotlinx.coroutines.flow.Flow
import ru.work_mate.rick_and_morty.domain.model.Character
import ru.work_mate.rick_and_morty.domain.model.Episode
import ru.work_mate.rick_and_morty.domain.model.Location

public infix fun Repository.`get character by id`(id: Character.Id): Flow<Result<Character>> =
    getObjectById(id)

public infix fun Repository.`get characters by ids`(ids: Set<Character.Id>): Flow<Result<Set<Character>>> =
    getObjectsByIds(ids)

public infix fun Repository.`get episode by id`(id: Episode.Id): Flow<Result<Episode>> =
    getObjectById(id)

public infix fun Repository.`get episodes by ids`(ids: Set<Episode.Id>): Flow<Result<Set<Episode>>> =
    getObjectsByIds(ids)

public infix fun Repository.`get location by id`(id: Episode.Id): Flow<Result<Location>> =
    getObjectById(id)

public infix fun Repository.`get locations by ids`(ids: Set<Location.Id>): Flow<Result<Set<Location>>> =
    getObjectsByIds(ids)

