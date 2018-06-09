package digital.wup.prefessor

import digital.wup.prefessor.test.JsName
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

    @JsName("putBoolean_putTrue_hasBeenSaved")
    @Test
    fun putBoolean_putTrue_hasBeenSaved() {

        prefessor.edit().putBoolean("test", true)
        prefessor.edit().apply()

        assertTrue(prefessor.getBoolean("test", false))
    }

    @JsName("putBoolean_putTrue_doNotSaveWithoutApply")
    @Test
    fun putBoolean_putTrue_doNotSaveWithoutApply() {
        prefessor.edit().putBoolean("test", true)

        assertFalse(prefessor.getBoolean("test", false))
    }

    @JsName("putBoolean_empty_returnsWithDefaultValue")
    @Test
    fun putBoolean_empty_returnsWithDefaultValue() {

        prefessor.getBoolean("test", true)
    }
}