package com.jeanbarrossilva.loadable.list

import java.io.Serializable

/** Converts this [SerializableList] into a [ListLoadable]. **/
fun <T : Serializable?> SerializableList<T>.asListLoadable(): ListLoadable<T> {
    return if (isEmpty()) ListLoadable.Empty() else ListLoadable.Populated(this)
}

/**
 * Creates a [SerializableList] without elements.
 *
 * @see serializableListOf
 **/
fun <T> emptySerializableList(): SerializableList<T> {
    return serializableListOf()
}

/**
 * Creates a new [SerializableList] with the given [elements].
 *
 * @param elements Elements to be added to the [SerializableList].
 **/
fun <T> serializableListOf(vararg elements: T): SerializableList<T> {
    val elementsAsList = elements.toList()
    return SerializableList(elementsAsList)
}