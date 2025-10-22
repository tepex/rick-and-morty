package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject

/**
 * @see https://rickandmortyapi.com/documentation/#location-schema
 * */
public interface Location : RmObject {
    override val id: Id
    override val name: Name
    override val url: Endpoint
    override val created: Created
    public val type: Type
    public val dimension: Dimension

    override fun validate() {}

    public interface Id : ObjectRoot.Id
    public interface Name : ObjectRoot.Name
    public interface Endpoint : ObjectRoot.Endpoint
    public interface Created : ObjectRoot.Created

    public interface Type : ValueObject.Value<String> {
        override fun validate() {}
    }

    public interface Dimension : ValueObject.Value<String> {
        override fun validate() {}
    }
}