package ru.work_mate.rick_and_morty.domain

import kotlinx.coroutines.flow.Flow
import ru.work_mate.rick_and_morty.domain.model.FilterQuery
import ru.work_mate.rick_and_morty.domain.model.ObjectRoot
import ru.work_mate.rick_and_morty.domain.model.RmItem
import ru.work_mate.rick_and_morty.domain.model.RmObject

public interface Repository {
    public fun <T : RmObject> getObjectById(id: ObjectRoot.Id): Flow<Result<T>>
    public fun <T : RmObject> getObjectsByIds(ids: Set<ObjectRoot.Id>): Flow<Result<Set<T>>>

    public fun fetchObjects(query: FilterQuery): Flow<Result<PageResult>>
}

public interface PageResult {

    public val items: List<RmItem>
}
