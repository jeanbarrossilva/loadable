package com.jeanbarrossilva.loadable.list.flow

import app.cash.turbine.test
import com.jeanbarrossilva.loadable.list.ListLoadable
import com.jeanbarrossilva.loadable.list.serializableListOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.test.runTest

internal class MutableStateFlowExtensionsTests {
    @Test
    fun `GIVEN a SerializableList WHEN creating a ListLoadable Flow with it THEN it's created`() {
        runTest {
            listLoadableFlowOf(serializableListOf(1, 2, 3)).test {
                assertEquals(ListLoadable.Populated(serializableListOf(1, 2, 3)), awaitItem())
            }
        }
    }

    @Test
    fun `GIVEN an Array WHEN creating a ListLoadable Flow with it THEN it's created`() {
        runTest {
            listLoadableFlowOf(1, 2, 3).test {
                assertEquals(ListLoadable.Populated(serializableListOf(1, 2, 3)), awaitItem())
            }
        }
    }

    @Test
    fun `GIVEN some content WHEN creating a ListLoadable StateFlow with it THEN it emits Loading followed by the matching ListLoadable`() { // ktlint-disable max-line-length
        runTest {
            listLoadableFlow(this) { load(1, 2, 3) }.test {
                assertIs<ListLoadable.Loading<Int>>(awaitItem())
                assertEquals(ListLoadable.Populated(serializableListOf(1, 2, 3)), awaitItem())
            }
        }
    }
}
