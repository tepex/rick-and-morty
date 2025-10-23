package ru.work_mate.rick_and_morty.domain.model.impl

import ru.it_arch.k3dm.ValueObject
import ru.work_mate.rick_and_morty.domain.model.Episode
import ru.work_mate.rick_and_morty.domain.model.FilterQuery
import ru.work_mate.rick_and_morty.domain.model.Location

@ConsistentCopyVisibility
public data class FilterQueryImpl private constructor(
    override val filter: FilterQuery.Filter?,
    override val page: FilterQuery.Page?
) : FilterQuery {

    init {
        validate()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ValueObject.Data> fork(vararg args: Any?): T =
        Builder().apply {
            filter = args[0] as FilterQuery.Filter?
            page = args[1] as FilterQuery.Page?
        }.build() as T

    public class Builder {
        public var filter: FilterQuery.Filter? = null
        public var page: FilterQuery.Page? = null

        public fun build(): FilterQuery =
            FilterQueryImpl(filter, page)
    }

    @ConsistentCopyVisibility
    public data class LocationFilterImpl private constructor(
        override val name: Location.Name,
        override val type: Location.Type
    ) : FilterQuery.Filter.LocationFilter {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Data> fork(vararg args: Any?): T =
            Builder().apply {
                name = args[0] as Location.Name
                type = args[1] as Location.Type
            }.build() as T

        public class Builder {
            public var name: Location.Name? = null
            public var type: Location.Type? = null

            public fun build(): FilterQuery.Filter.LocationFilter {
                requireNotNull(name) { "LocationFilter.name must not be null!" }
                requireNotNull(type) { "LocationFilter.type must not be null!" }

                return LocationFilterImpl(name!!, type!!)
            }
        }

        public class DslBuilder {
            public var name: String? = null
            public var type: String? = null

            public fun build(): FilterQuery.Filter.LocationFilter {
                requireNotNull(name) { "LocationFilter.name must not be null!" }
                requireNotNull(type) { "LocationFilter.type must not be null!" }

                return LocationFilterImpl(
                    LocationImpl.NameImpl(name!!),
                    LocationImpl.TypeImpl(type!!)
                )
            }
        }
    }

    @ConsistentCopyVisibility
    public data class EpisodeFilterImpl private constructor(
        override val name: Episode.Name,
        override val episode: Episode.Code
    ) : FilterQuery.Filter.EpisodeFilter {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Data> fork(vararg args: Any?): T =
            Builder().apply {
                name = args[0] as Episode.Name
                episode = args[1] as Episode.Code
            }.build() as T

        public class Builder {
            public var name: Episode.Name? = null
            public var episode: Episode.Code? = null

            public fun build(): FilterQuery.Filter.EpisodeFilter {
                requireNotNull(name) { "EpisodeFilter.name must not be null!" }
                requireNotNull(episode) { "EpisodeFilter.episode must not be null!" }

                return EpisodeFilterImpl(name!!, episode!!)
            }
        }

        public class DslBuilder {
            public var name: String? = null
            public var episode: String? = null

            public fun build(): FilterQuery.Filter.EpisodeFilter {
                requireNotNull(name) { "EpisodeFilter.name must not be null!" }
                requireNotNull(episode) { "EpisodeFilter.episode must not be null!" }

                return EpisodeFilterImpl(EpisodeImpl.NameImpl(name!!), EpisodeImpl.CodeImpl(episode!!))
            }
        }
    }

    @JvmInline
    public value class PageImpl(override val boxed: String) : FilterQuery.Page {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<String>> apply(boxed: String): T =
            PageImpl(boxed) as T

        override fun toString(): String =
            boxed

        public companion object {
            public operator fun invoke(value: String): FilterQuery.Page =
                PageImpl(value)
        }
    }
}