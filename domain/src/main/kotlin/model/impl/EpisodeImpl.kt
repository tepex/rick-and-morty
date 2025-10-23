package ru.work_mate.rick_and_morty.domain.model.impl

import kotlinx.datetime.LocalDate
import ru.it_arch.k3dm.ValueObject
import ru.work_mate.rick_and_morty.domain.model.Character
import ru.work_mate.rick_and_morty.domain.model.Episode
import java.net.URL
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ConsistentCopyVisibility
@OptIn(ExperimentalTime::class)
public data class EpisodeImpl private constructor(
    override val id: Episode.Id,
    override val name: Episode.Name,
    override val url: Episode.Endpoint,
    override val created: Episode.Created,
    override val airDate: Episode.AirDate,
    override val episode: Episode.Code,
    override val characters: List<Character>
) : Episode {
    
    init {
        validate()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ValueObject.Data> fork(vararg args: Any?): T =
        Builder().apply {
            id = args[0] as Episode.Id
            name = args[1] as Episode.Name
            url = args[2] as Episode.Endpoint
            created = args[3] as Episode.Created
            airDate = args[4] as Episode.AirDate
            episode = args[5] as Episode.Code
            characters = args[6] as List<Character>
        }.build() as T
    
    public class Builder {
        public var id: Episode.Id? = null
        public var name: Episode.Name? = null
        public var url: Episode.Endpoint? = null
        public var created: Episode.Created? = null
        public var airDate: Episode.AirDate? = null
        public var episode: Episode.Code? = null
        public var characters: List<Character> = emptyList()

        public fun build(): Episode {
            requireNotNull(id) { "Episode.id must not be null!" }
            requireNotNull(name) { "Episode.name must not be null!" }
            requireNotNull(url) { "Episode.url must not be null!" }
            requireNotNull(created) { "Episode.created must not be null!" }
            requireNotNull(airDate) { "Episode.airDate must not be null!" }
            requireNotNull(episode) { "Episode.episode must not be null!" }
            require(characters.isNotEmpty()) { "Episode.characters must not be empty!" }

            return EpisodeImpl(id!!, name!!, url!!, created!!, airDate!!, episode!!, characters)
        }
    }

    public class DslBuilder {
        public var id: Int? = null
        public var name: String? = null
        public var url: String? = null
        public var created: String? = null
        public var airDate: String? = null
        public var episode: String? = null
        public var characters: List<Character> = emptyList()

        public fun build(): Episode {
            requireNotNull(id) { "Episode.id must not be null!" }
            requireNotNull(name) { "Episode.name must not be null!" }
            requireNotNull(url) { "Episode.url must not be null!" }
            requireNotNull(created) { "Episode.created must not be null!" }
            requireNotNull(airDate) { "Episode.airDate must not be null!" }
            requireNotNull(episode) { "Episode.episode must not be null!" }
            require(characters.isNotEmpty()) { "Episode.characters must not be empty!" }

            return EpisodeImpl(
                IdImpl(id!!),
                NameImpl(name!!),
                EndpointImpl( URL(url!!)),
                CreatedImpl(Instant.parse(created!!)),
                AirDateImpl(LocalDate.parse(airDate!!)),
                CodeImpl(episode!!),
                characters
            )
        }
    }

    @JvmInline
    public value class IdImpl(override val boxed: Int) : Episode.Id {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<Int>> apply(boxed: Int): T =
            IdImpl(boxed) as T

        override fun toString(): String =
            boxed.toString()
        
        public companion object {
            public operator fun invoke(value: Int): Episode.Id =
                IdImpl(value)
        }
    }

    @JvmInline
    public value class NameImpl private constructor(override val boxed: String) : Episode.Name {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<String>> apply(boxed: String): T =
            NameImpl(boxed) as T

        override fun toString(): String =
            boxed

        public companion object {
            public operator fun invoke(value: String): Episode.Name =
                NameImpl(value)
        }
    }

    @JvmInline
    public value class EndpointImpl private constructor(override val boxed: URL) : Episode.Endpoint {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<URL>> apply(boxed: URL): T =
            EndpointImpl(boxed) as T

        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: URL): Episode.Endpoint =
                EndpointImpl(value)
        }
    }

    @JvmInline
    public value class CreatedImpl private constructor(override val boxed: Instant) : Episode.Created {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<Instant>> apply(boxed: Instant): T =
            CreatedImpl(boxed) as T

        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: Instant): Episode.Created =
                CreatedImpl(value)
        }
    }

    @JvmInline
    public value class AirDateImpl private constructor(override val boxed: LocalDate) : Episode.AirDate {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<LocalDate>> apply(boxed: LocalDate): T =
            AirDateImpl(boxed) as T

        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: LocalDate): Episode.AirDate =
                AirDateImpl(value)
        }
    }
    
    @JvmInline
    public value class CodeImpl private constructor(override val boxed: String) : Episode.Code {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<String>> apply(boxed: String): T =
            CodeImpl(boxed) as T
        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: String): Episode.Code =
                CodeImpl(value)
        }
    }
}
