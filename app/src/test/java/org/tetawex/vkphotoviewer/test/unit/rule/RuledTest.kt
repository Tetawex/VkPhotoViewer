package org.tetawex.vkphotoviewer.test.unit.rule

import org.junit.Rule
import org.mockito.junit.MockitoJUnit

/**
 * Created by Tetawex on 15.04.2018.
 */
open class RuledTest {
    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val rxRule = RxImmediateSchedulerRule()
}