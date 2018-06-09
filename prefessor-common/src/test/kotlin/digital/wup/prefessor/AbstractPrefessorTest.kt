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
        // when
        prefessor.edit().putBoolean("test_save_true", true)
        prefessor.edit().apply()

        // then
        assertTrue(prefessor.getBoolean("test_save_true", false))
    }

    @JsName("putBoolean_putTrue_doNotSaveWithoutApply")
    @Test
    fun putBoolean_putTrue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putBoolean("test_not_save_true", true)

        // then
        assertFalse(prefessor.getBoolean("test_not_save_true", false))
    }

    @JsName("putBoolean_empty_returnsWithDefaultValue")
    @Test
    fun putBoolean_empty_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()

        // then
        assertTrue(prefessor.getBoolean("test_retrive_default", true))
    }

    @Test
    fun remove_addAndRemoveValue_success() {
        // when
        prefessor.edit().putBoolean("add_and_remove", true)
        prefessor.edit().apply()

        // and
        prefessor.edit().remove("add_and_remove")
        prefessor.edit().apply()

        // than
        assertFalse(prefessor.getBoolean("add_and_remove", false))
    }

    @Test
    fun clean_addAndClearValue_success() {
        // when
        prefessor.edit().putBoolean("add_and_clear", true)
        prefessor.edit().apply()

        // and
        prefessor.edit().clear()
        prefessor.edit().apply()

        // than
        assertFalse(prefessor.getBoolean("add_and_clear", false))
    }
}