package digital.wup.prefessor

import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

abstract class AbstractPrefessorTest {

    protected lateinit var prefessor: Prefessor

    @BeforeTest()
    open fun setup() {
        prefessor = Prefessor.create()
    }

    @Test
    fun test_putBoolean_putTrue_hasBeenSaved() {

        prefessor.edit().putBoolean("test", true)
        prefessor.edit().apply()

        assertTrue(prefessor.getBoolean("test"))
    }

    @Test
    fun test_putBoolean_putTrue_doNotSaveWithoutApply() {
        prefessor.edit().putBoolean("test", true)

        assertFalse(prefessor.getBoolean("test"))
    }
}