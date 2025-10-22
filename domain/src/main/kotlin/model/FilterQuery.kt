package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject

public interface FilterQuery : ValueObject.Data {
    public val filter: Filter
    public val page: Page

    public sealed interface Filter {
        public val name: ObjectRoot.Name

        public data class LocationFilter(
            override val name: Location.Name,
            val type: Location.Type
        ) : Filter

        public data class EpisodeFilter(
            override val name: Episode.Name,
            val episode: Episode.Code
        ) : Filter

        public data class CharacterFilter(
            override val name: Character.Name,
            val status: Character.Status,
            val species: Character.Species,
            val type: Character.Type,
            val gender: Character.Gender
        ) : Filter
    }

    public interface Page : ValueObject.Value<Int> {
        override fun validate() {
            require(boxed > 0) { "Page must be > 0" }
        }
    }
}