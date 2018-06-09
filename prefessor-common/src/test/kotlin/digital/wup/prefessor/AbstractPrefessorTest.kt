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

    @JsName("putInt_afterClear_returnsWithDefaultValue")
    @Test
    fun getInt_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1, prefessor.getInt(KEY, 1))
    }

    @JsName("putInt_saveValue_hasBeenSaved")
    @Test
    fun putInt_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putInt(KEY, 30)
        prefessor.edit().apply()

        // then
        assertEquals(30, prefessor.getInt(KEY, 10))
    }

    @JsName("putInt_putTrue_doNotSaveWithoutApply")
    @Test
    fun putInt_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putInt(KEY, 7)

        // then
        assertEquals(1, prefessor.getInt(KEY, 1))
    }

    @JsName("putLong_afterClear_returnsWithDefaultValue")
    @Test
    fun getLong_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1L, prefessor.getLong(KEY, 1L))
    }

    @JsName("putLong_saveValue_hasBeenSaved")
    @Test
    fun putLong_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putLong(KEY, 30L)
        prefessor.edit().apply()

        // then
        assertEquals(30L, prefessor.getLong(KEY, 10L))
    }

    @JsName("putLong_putTrue_doNotSaveWithoutApply")
    @Test
    fun putLong_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putLong(KEY, 7L)

        // then
        assertEquals(1L, prefessor.getLong(KEY, 1L))
    }

    @JsName("putString_afterClear_returnsWithDefaultValue")
    @Test
    fun getString_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals("default", prefessor.getString(KEY, "default"))
    }

    @JsName("putString_saveValue_hasBeenSaved")
    @Test
    fun putString_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putString(KEY, "test_value")
        prefessor.edit().apply()

        // then
        assertEquals("test_value", prefessor.getString(KEY, "default"))
    }

    @JsName("putString_putTrue_doNotSaveWithoutApply")
    @Test
    fun putString_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putString(KEY, "test_value")

        // then
        assertEquals("default", prefessor.getString(KEY, "default"))
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