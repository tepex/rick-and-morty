package ru.work_mate.rick_and_morty.domain.model.impl

import ru.it_arch.k3dm.ValueObject
import ru.work_mate.rick_and_morty.domain.model.Location
import java.net.URL
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ConsistentCopyVisibility
@OptIn(ExperimentalTime::class)
public data class LocationImpl private constructor(
    override val id: Location.Id,
    override val name: Location.Name,
    override val url: Location.Endpoint,
    override val created: Location.Created,
    override val type: Location.Type,
    override val dimension: Location.Dimension
) : Location {

    init {
        validate()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ValueObject.Data> fork(vararg args: Any?): T =
        Builder().apply {
            id = args[0] as Location.Id
            name = args[1] as Location.Name
            url = args[2] as Location.Endpoint
            created = args[3] as Location.Created
            type = args[4] as Location.Type
            dimension = args[5] as Location.Dimension
        }.build() as T

    public class DslBuilder {
        public var id: Int? = null
        public var name: String? = null
        public var url: String? = null
        public var created: String? = null
        public var type: String? = null
        public var dimension: String? = null

        public fun build(): Location {
            requireNotNull(id) { "Location.id must not be null!" }
            requireNotNull(name) { "Location.name must not be null!" }
            requireNotNull(url) { "Location.url must not be null!" }
            requireNotNull(created) { "Location.created must not be null!" }
            requireNotNull(type) { "Location.type must not be null!" }
            requireNotNull(dimension) { "Location.dimension must not be null!" }

            return LocationImpl(
                IdImpl(id!!),
                NameImpl(name!!),
                EndpointImpl(URL( url!!)),
                CreatedImpl(Instant.parse(created!!)),
                TypeImpl(type!!),
                DimensionImpl(dimension!!)
            )
        }
    }
    
    public class Builder {
        public var id: Location.Id? = null
        public var name: Location.Name? = null
        public var url: Location.Endpoint? = null
        public var created: Location.Created? = null
        public var type: Location.Type? = null
        public var dimension: Location.Dimension? = null

        public fun build(): Location {
            requireNotNull(id) { "Location.id must not be null!" }
            requireNotNull(name) { "Location.name must not be null!" }
            requireNotNull(url) { "Location.url must not be null!" }
            requireNotNull(created) { "Location.created must not be null!" }
            requireNotNull(type) { "Location.type must not be null!" }
            requireNotNull(dimension) { "Location.dimension must not be null!" }

            return LocationImpl(id!!, name!!, url!!, created!!, type!!, dimension!!)
        }
        
    }

    @JvmInline
    public value class IdImpl private constructor(override val boxed: Int) : Location.Id {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<Int>> apply(boxed: Int): T =
            IdImpl(boxed) as T

        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: Int): Location.Id =
                IdImpl(value)
        }
    }

    @JvmInline
    public value class NameImpl private constructor(override val boxed: String) : Location.Name {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<String>> apply(boxed: String): T =
            NameImpl(boxed) as T

        override fun toString(): String =
            boxed
        
        public companion object {
            public operator fun invoke(value: String): Location.Name =
                NameImpl(value)
        }
    }
    
    @JvmInline
    public value class EndpointImpl private constructor(override val boxed: URL) : Location.Endpoint {
        
        init {
            validate()
        }
        
        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<URL>> apply(boxed: URL): T =
            EndpointImpl(boxed) as T

        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: URL): Location.Endpoint =
                EndpointImpl(value)
        }
    }
    
    @JvmInline
    public value class CreatedImpl private constructor(override val boxed: Instant) : Location.Created {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<Instant>> apply(boxed: Instant): T =
            CreatedImpl(boxed) as T
        
        override fun toString(): String =
            boxed.toString()

        public companion object {
            public operator fun invoke(value: Instant): Location.Created =
                CreatedImpl(value)
        }
    }
    
    @JvmInline
    public value class TypeImpl private constructor(override val boxed: String) : Location.Type {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<String>> apply(boxed: String): T =
            TypeImpl(boxed) as T

        override fun toString(): String =
            boxed
        
        public companion object {
            public operator fun invoke(value: String): Location.Type =
                TypeImpl(value)
        }
    }
    
    @JvmInline
    public value class DimensionImpl private constructor(override val boxed: String) : Location.Dimension {

        init {
            validate()
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ValueObject.Value<String>> apply(boxed: String): T =
            DimensionImpl(boxed) as T

        override fun toString(): String =
            boxed
        
        public companion object {
            public operator fun invoke(value: String): Location.Dimension =
                DimensionImpl(value)
        }
    }
}
