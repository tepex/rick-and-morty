package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject

public interface FilterQuery : ValueObject.Data {
    public val filter: Filter?
    public val page: Page?

    override fun validate() {}

    public sealed interface Filter : ValueObject.Data {
        public val name: ObjectRoot.Name

        public interface LocationFilter : Filter {
            override val name: Location.Name
            public val type: Location.Type

            override fun validate() {}
        }

        public interface EpisodeFilter : Filter {
            override val name: Episode.Name
            public val episode: Episode.Code

            override fun validate() {}
        }

        public interface CharacterFilter : Filter {
            override val name: Character.Name
            public val status: Character.Status
            public val species: Character.Species
            public val type: Character.Type
            public val gender: Character.Gender

            override fun validate() {}
        }
    }

    public interface Page : ValueObject.Value<String> {
        override fun validate() {}
    }
}