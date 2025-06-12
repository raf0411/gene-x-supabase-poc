package kalbe.corp.genexsupabasepoc

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * A simple "canary" test to see if the test runner is configured correctly.
 * If you can't see the green "run" icons for this test, the problem is
 * in the project's build.gradle or folder structure.
 */
@RunWith(AndroidJUnit4::class)
class SimpleDiscoveryTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}