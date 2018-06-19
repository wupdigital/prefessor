package digital.wup.prefessor

import digital.wup.prefessor.utils.PrefessorProvider
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

abstract class AbstractPrefessorTest {

    protected lateinit var prefessor: Prefessor

    @BeforeTest()
    open fun setup() {
        prefessor = PrefessorProvider.get()
        prefessor.edit().clear()
        prefessor.edit().apply()
        // Clear again, because clear runs before modification operations
        prefessor.edit().clear()
        prefessor.edit().apply()
    }

    @Test
    fun getBoolean_clearAndApply_returnDefaultValue() {
        val key = "getBoolean_clearAndApply_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()

        // then
        assertTrue { prefessor.getBoolean(key, true) }
    }

    @Test
    fun getBoolean_putTrueAndApply_saveSuccessfullyAndReturnSavedValue() {
        val key = "getBoolean_putTrueAndApply_saveSuccessfullyAndReturnSavedValue"

        // when
        prefessor.edit().putBoolean(key, true)
        prefessor.edit().apply()

        // then
        assertTrue { prefessor.getBoolean(key, false) }
    }

    @Test
    fun getBoolean_putTrueWithoutApply_notSaveValueAndReturnWithDefault() {
        val key = "putBoolean_putTrueWithoutApply_notSaveValueAndReturnWithDefault"

        // when
        prefessor.edit().putBoolean(key, true)

        // then
        assertFalse { prefessor.getBoolean(key, false) }
    }

    @Test
    fun getFloat_clearAndApply_returnDefaultValue() {
        val key = "getFloat_clearAndApply_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1.0f, prefessor.getFloat(key, 1.0f))
    }

    @Test
    fun getFloat_putFloatAndApply_saveSuccessfullyAndReturnSavedValue() {
        val key = "getFloat_putFloatAndApply_saveSuccessfullyAndReturnSavedValue"

        // when
        prefessor.edit().putFloat(key, 30.0f)
        prefessor.edit().apply()

        // then
        assertEquals(30.0f, prefessor.getFloat(key, 10.0f))
    }

    @Test
    fun getFloat_putFloatWithoutApply_notSaveValueAndReturnWithDefault() {
        val key = "getFloat_putFloatWithoutApply_notSaveValueAndReturnWithDefault"

        // when
        prefessor.edit().putFloat(key, 7.0f)

        // then
        assertEquals(1.0f, prefessor.getFloat(key, 1.0f))
    }

    @Test
    fun getInt_clearAndApply_returnDefaultValue() {
        val key = "getInt_clearAndApply_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1, prefessor.getInt(key, 1))
    }

    @Test
    fun getInt_putIntAndApply_saveSuccessfullyAndReturnSavedValue() {
        val key = "getInt_putIntAndApply_saveSuccessfullyAndReturnSavedValue"

        // when
        prefessor.edit().putInt(key, 30)
        prefessor.edit().apply()

        // then
        assertEquals(30, prefessor.getInt(key, 10))
    }

    @Test
    fun getInt_putIntWithoutApply_notSaveValueAndReturnWithDefault() {
        val key = "getInt_putIntWithoutApply_notSaveValueAndReturnWithDefault"

        // when
        prefessor.edit().putInt(key, 7)

        // then
        assertEquals(1, prefessor.getInt(key, 1))
    }

    @Test
    fun getLong_clearAndApply_returnDefaultValue() {
        val key = "getLong_clearAndApply_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1L, prefessor.getLong(key, 1L))
    }

    @Test
    fun getLong_putLongAndApply_saveSuccessfullyAndReturnSavedValue() {
        val key = "getLong_putLongAndApply_saveSuccessfullyAndReturnSavedValue"

        // when
        prefessor.edit().putLong(key, 30L)
        prefessor.edit().apply()

        // then
        assertEquals(30L, prefessor.getLong(key, 10L))
    }

    @Test
    fun getLong_putLongWithoutApply_notSaveValueAndReturnWithDefault() {
        val key = "getLong_putLongWithoutApply_notSaveValueAndReturnWithDefault"

        // when
        prefessor.edit().putLong(key, 7L)

        // then
        assertEquals(1L, prefessor.getLong(key, 1L))
    }

    @Test
    fun getString_clearAndApply_returnDefaultValue() {
        val key = "getString_clearAndApply_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals("default", prefessor.getString(key, "default"))
    }

    @Test
    fun getString_putStringAndApply_saveSuccessfullyAndReturnSavedValue() {
        val key = "getString_putStringAndApply_saveSuccessfullyAndReturnSavedValue"

        // when
        prefessor.edit().putString(key, "test_value")
        prefessor.edit().apply()

        // then
        assertEquals("test_value", prefessor.getString(key, "default"))
    }

    @Test
    fun getString_putStringWithoutApply_notSaveValueAndReturnWithDefault() {
        val key = "getString_putStringWithoutApply_notSaveValueAndReturnWithDefault"

        // when
        prefessor.edit().putString(key, "test_value")

        // then
        assertEquals("default",
                prefessor.getString(key, "default"))
    }

    @Test
    fun remove_putBooleanAndRemove_clearSuccess() {
        val key = "remove_putBooleanAndRemove_clearSuccess"

        // when
        prefessor.edit().putBoolean(key, true)
        prefessor.edit().apply()

        // and
        prefessor.edit().remove(key)
        prefessor.edit().apply()

        // than
        assertFalse { prefessor.getBoolean(key, false) }
    }

    @Test
    fun clean_putBooleanAndClear_clearSuccess() {
        val key = "clean_putBooleanAndClear_clearSuccess"

        // when
        prefessor.edit().putBoolean(key, true)
        prefessor.edit().apply()

        // and
        prefessor.edit().clear()
        prefessor.edit().apply()

        // than
        assertFalse { prefessor.getBoolean(key, false) }
    }

    @Test
    fun clear_putValueBeforeClear_cleanRunBeforeModifyOperations() {
        val key = "clear_putValueBeforeClear_cleanRunBeforeModifyOperations"

        // when
        prefessor.edit().putBoolean(key, true)
        prefessor.edit().clear()
        prefessor.edit().apply()

        // than
        assertTrue { prefessor.getBoolean(key, false) }
    }
}