package com.jeanbarrossilva.loadable

import java.io.NotSerializableException

/** Scope through which [Loadable]s are sent. **/
abstract class LoadableScope<T> internal constructor() {
    /**
     * Determines whether loaded content should be serializable.
     *
     * @see load
     **/
    internal open val serializability = Serializability.default

    /** Sends a [Loadable.Loading]. **/
    suspend fun load() {
        send(Loadable.Loading())
    }

    /**
     * Sends a [Loadable.Loaded] with the given [content].
     *
     * @param content Value to be set as the [Loadable.Loaded.content].
     * @throws NotSerializableException If [serializability] is [enforced][Serializability.ENFORCED]
     * and the [content] cannot be serialized.
     **/
    @Throws(NotSerializableException::class)
    suspend fun load(content: T) {
        send(Loadable.Loaded(content, serializability))
    }

    /**
     * Sends a [Loadable.Failed] with the given [error].
     *
     * @param error [Throwable] to be set as the [Loadable.Failed.error].
     **/
    suspend fun fail(error: Throwable) {
        send(Loadable.Failed(error))
    }

    /**
     * Sends the given [loadable].
     *
     * @param loadable [Loadable] to be sent.
     **/
    internal abstract suspend fun send(loadable: Loadable<T>)
}
