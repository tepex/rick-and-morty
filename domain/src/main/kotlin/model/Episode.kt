package ru.work_mate.rick_and_morty.domain.model

import kotlinx.datetime.LocalDate
import ru.it_arch.k3dm.ValueObject

/**
 * Модель сущности Episode.
 *
 * @see https://rickandmortyapi.com/documentation/#episode-schema
 * */
public interface Episode : RmObject {
    override val id: Id
    override val name: Name
    override val url: Endpoint
    override val created: Created
    public val airDate: AirDate
    public val episode: Code
    public val characters: List<Character>

    override fun validate() {}

    public interface Id : ObjectRoot.Id
    public interface Name : ObjectRoot.Name
    public interface Endpoint : ObjectRoot.Endpoint
    public interface Created : ObjectRoot.Created

    public interface AirDate : ValueObject.Value<LocalDate> {
        override fun validate() {}
    }

    public interface Code : ValueObject.Value<String> {
        override fun validate() {
            require(RE.matches(boxed)) { "Episode.code must match RE $RE" }
        }

        private companion object {
            val RE = "S\\d\\dE\\d\\d".toRegex()
        }
    }
}