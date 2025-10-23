package ru.work_mate.rick_and_morty.domain.model.impl

import ru.work_mate.rick_and_morty.domain.model.FilterQuery
import ru.work_mate.rick_and_morty.domain.model.Location

public fun location(block: LocationImpl.DslBuilder.() -> Unit): Location =
    LocationImpl.DslBuilder().apply(block).build()

public fun locationFilter(block: FilterQueryImpl.LocationFilterImpl.DslBuilder.() -> Unit): FilterQuery.Filter.LocationFilter =
    FilterQueryImpl.LocationFilterImpl.DslBuilder().apply(block).build()