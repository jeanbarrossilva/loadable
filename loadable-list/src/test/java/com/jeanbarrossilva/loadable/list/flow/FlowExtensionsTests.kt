package com.jeanbarrossilva.loadable.list.flow

import app.cash.turbine.test
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.flow.loadableFlow
import com.jeanbarrossilva.loadable.list.ListLoadable
import com.jeanbarrossilva.loadable.list.SerializableList
import com.jeanbarrossilva.loadable.list.serializableListOf
import com.jeanbarrossilva.loadable.list.toListLoadable
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest

internal class FlowExtensionsTests {
    @Test
    fun `GIVEN some content WHEN creating ListLoadable Flow with it THEN it emits Loading followed by the matching ListLoadable`() { // ktlint-disable max-line-length
        runTest {
            listLoadableFlow { load(1, 2, 3) }.test {
                assertIs<ListLoadable.Loading<Int>>(awaitItem())
                assertEquals(ListLoadable.Populated(serializableListOf(1, 2, 3)), awaitItem())
                awaitComplete()
            }
        }
    }

    @Test
    fun `GIVEN a ListLoadable Flow WHEN filtering by non-loading values THEN it's filtered`() {
        runTest {
            loadableFlow {
                load(serializableListOf(1))
                load()
                load(serializableListOf(1, 2))
                fail(Exception())
            }
                .map(Loadable<SerializableList<Int>>::toListLoadable)
                .filterNotLoading()
                .test {
                    assertEquals(ListLoadable.Populated(serializableListOf(1)), awaitItem())
                    assertEquals(ListLoadable.Populated(serializableListOf(1, 2)), awaitItem())
                    assertIs<ListLoadable.Failed<Int>>(awaitItem())
                    awaitComplete()
                }
        }
    }

    @Test
    fun `GIVEN a Flow WHEN converting it into a ListLoadable Flow THEN the initial value is loading`() { // ktlint-disable max-line-length
        runTest {
            flowOf(listOf(1, 2, 3)).listLoadable(this, SharingStarted.Lazily).test {
                assertIs<ListLoadable.Loading<Int>>(awaitItem())
            }
        }
    }

    @Test
    fun `GIVEN a Flow with an empty list WHEN converting it into a ListLoadable THEN it is empty`() { // ktlint-disable max-line-length
        runTest {
            flowOf(emptyList<Int>())
                .listLoadable(this, SharingStarted.Lazily)
                .filterNotLoading()
                .test { assertIs<ListLoadable.Empty<Int>>(awaitItem()) }
        }
    }

    @Test
    fun `GIVEN a Flow with a populated list WHEN converting it into a ListLoadable THEN it is populated`() { // ktlint-disable max-line-length
        runTest {
            flowOf(listOf(1, 2, 3))
                .listLoadable(this, SharingStarted.Lazily)
                .filterNotLoading()
                .test {
                    assertEquals(ListLoadable.Populated(serializableListOf(1, 2, 3)), awaitItem())
                }
        }
    }

    @Test
    fun `GIVEN a failed Flow WHEN converting it into a ListLoadable THEN it is failed`() { // ktlint-disable max-line-length
        runTest {
            loadableFlow<SerializableList<Int>> { fail(Exception()) }
                .map(Loadable<SerializableList<Int>>::toListLoadable)
                .filterNotLoading()
                .test {
                    assertIs<ListLoadable.Failed<Int>>(awaitItem())
                    awaitComplete()
                }
        }
    }
}
