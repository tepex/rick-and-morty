package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject

public interface CharacterItem : RmItem {
    override val id: Character.Id
    override val name: Character.Name
    override val url: Character.Endpoint
    override val created: Character.Created
    public val status: Character.Status
    public val species: Character.Species
    public val type: Character.Type
    public val gender: Character.Gender
    public val origin: LocationLink
    public val location: LocationLink
    public val image: Character.ImageUrl
    public val episodes: List<EpisodeLink>

    override fun validate() {}

    public interface LocationLink : ValueObject.Data {
        public val name: Location.Name
        public val url: Location.Endpoint
    }

    public interface EpisodeLink : ValueObject.Data {
        public val name: Episode.Name
        public val url: Episode.Endpoint
    }
}