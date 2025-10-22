package ru.work_mate.rick_and_morty.domain.model

import ru.it_arch.k3dm.ValueObject
import java.net.URL
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 *
 * */
public interface ObjectRoot : ValueObject.Data {
    public val id: Id
    public val name: Name
    public val url: Endpoint
    public val created: Created

    public interface Id : ValueObject.Value<Int> {
        override fun validate() {
            require(boxed > 0) { "Id must be > 0" }
        }
    }

    public interface Name : ValueObject.Value<String> {
        override fun validate() {}
    }

    public interface Endpoint : ValueObject.Value<URL> {
        override fun validate() {}
    }

    @OptIn(ExperimentalTime::class)
    public interface Created : ValueObject.Value<Instant> {
        override fun validate() {}
    }
}