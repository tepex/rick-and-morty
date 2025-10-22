package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject
import java.net.URL

/**
 * Character schema
 * Key	Type	Description
 * id	int	The id of the character.
 * name	string	The name of the character.
 * status	string	The status of the character ('Alive', 'Dead' or 'unknown').
 * species	string	The species of the character.
 * type	string	The type or subspecies of the character.
 * gender	string	The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
 * origin	object	Name and link to the character's origin location.
 * location	object	Name and link to the character's last known location endpoint.
 * image	string (url)	Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
 * episode	array (urls)	List of episodes in which this character appeared.
 * url	string (url)	Link to the character's own URL endpoint.
 * created	string	Time at which the character was created in the database.
 *
 * @see https://rickandmortyapi.com/documentation/#character-schema
 **/
public interface Character : RmObject {
    override val id: Id
    override val name: Name
    override val url: Endpoint
    override val created: Created
    public val status: Status
    public val species: Species
    public val type: Type
    public val gender: Gender
    public val origin: Location
    public val location: Location
    public val image: ImageUrl

    override fun validate() {}

    public interface Id : ObjectRoot.Id
    public interface Name : ObjectRoot.Name
    public interface Endpoint : ObjectRoot.Endpoint
    public interface Created : ObjectRoot.Created

    public enum class Status {
        ALIVE, DEAD, UNKNOWN
    }

    public enum class Gender {
        MALE, FEMALE, GENDERLESS, UNKNOWN
    }

    public interface Species : ValueObject.Value<String> {
        override fun validate() {}
    }

    public interface Type : ValueObject.Value<String> {
        override fun validate() {}
    }

    public interface ImageUrl : ValueObject.Value<URL> {
        override fun validate() {}
    }
}