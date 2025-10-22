package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject

public interface LocationItem : RmItem {
    override val id: Location.Id
    override val name: Location.Name
    override val url: Location.Endpoint
    override val created: Location.Created
    public val type: Location.Type
    public val dimension: Location.Dimension
    public val residents: List<CharacterLink>

    override fun validate() {}

    public interface CharacterLink : ValueObject.Data {
        public val name: Character.Name
        public val url: Character.Endpoint
    }
}