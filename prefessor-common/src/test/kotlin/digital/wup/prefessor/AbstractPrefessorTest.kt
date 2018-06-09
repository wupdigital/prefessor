package digital.wup.prefessor

import digital.wup.prefessor.test.JsName
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

abstract class AbstractPrefessorTest {

    protected lateinit var prefessor: Prefessor

    companion object {
        private const val KEY = "test_key"
    }

    @BeforeTest()
    open fun setup() {
        prefessor = Prefessor.create()
        prefessor.edit().clear()
        prefessor.edit().apply()
    }

    @JsName("putBoolean_afterClear_returnsWithDefaultValue")
    @Test
    fun getBoolean_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()

        // then
        assertTrue(prefessor.getBoolean(KEY, true))
    }

    @JsName("putBoolean_putTrue_hasBeenSaved")
    @Test
    fun putBoolean_putTrue_hasBeenSaved() {
        // when
        prefessor.edit().putBoolean(KEY, true)
        prefessor.edit().apply()

        // then
        assertTrue(prefessor.getBoolean(KEY, false))
    }

    @JsName("putBoolean_putTrue_doNotSaveWithoutApply")
    @Test
    fun putBoolean_putTrue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putBoolean(KEY, true)

        // then
        assertFalse(prefessor.getBoolean(KEY, false))
    }

    @JsName("putFloat_afterClear_returnsWithDefaultValue")
    @Test
    fun getFloat_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1.0f, prefessor.getFloat(KEY, 1.0f))
    }

    @JsName("putFloat_saveValue_hasBeenSaved")
    @Test
    fun putFloat_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putFloat(KEY, 30.0f)
        prefessor.edit().apply()

        // then
        assertEquals(30.0f, prefessor.getFloat(KEY, 10.0f))
    }

    @JsName("putFloat_putTrue_doNotSaveWithoutApply")
    @Test
    fun putFloat_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putFloat(KEY, 7.0f)

        // then
        assertEquals(1.0f, prefessor.getFloat(KEY, 1.0f))
    }

    @Test
    fun remove_addAndRemoveValue_success() {
        // when
        prefessor.edit().putBoolean(KEY, true)
        prefessor.edit().apply()

        // and
        prefessor.edit().remove(KEY)
        prefessor.edit().apply()

        // than
        assertFalse(prefessor.getBoolean(KEY, false))
    }

    @Test
    fun clean_addAndClearValue_success() {
        // when
        prefessor.edit().putBoolean(KEY, true)
        prefessor.edit().apply()

        // and
        prefessor.edit().clear()
        prefessor.edit().apply()

        // than
        assertFalse(prefessor.getBoolean(KEY, false))
    }
}