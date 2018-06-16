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
    fun getBoolean_clearPrefs_returnDefaultValue() {
        val key = "getBoolean_clearPrefs_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()

        // then
        assertTrue { prefessor.getBoolean(key, true) }
    }

    @Test
    fun putBoolean_putTrue_saveSuccessfully() {
        val key = "putBoolean_putTrue_saveSuccessfully"

        // when
        prefessor.edit().putBoolean(key, true)
        prefessor.edit().apply()

        // then
        assertTrue { prefessor.getBoolean(key, false) }
    }

    @Test
    fun putBoolean_putTrue_notSaveValue() {
        val key = "putBoolean_putTrue_notSaveValue"

        // when
        prefessor.edit().putBoolean(key, true)

        // then
        assertFalse { prefessor.getBoolean(key, false) }
    }

    @Test
    fun getFloat_clearPrefs_returnDefaultValue() {
        val key = "getFloat_clearPrefs_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1.0f, prefessor.getFloat(key, 1.0f))
    }

    @Test
    fun putFloat_saveValue_saveSuccessfully() {
        val key = "putFloat_saveValue_saveSuccessfully"

        // when
        prefessor.edit().putFloat(key, 30.0f)
        prefessor.edit().apply()

        // then
        assertEquals(30.0f, prefessor.getFloat(key, 10.0f))
    }

    @Test
    fun putFloat_doNotApply_notSaveValue() {
         val key = "putFloat_doNotApply_notSaveValue"

        // when
        prefessor.edit().putFloat(key, 7.0f)

        // then
        assertEquals(1.0f, prefessor.getFloat(key, 1.0f))
    }

    @Test
    fun getInt_clearPrefs_returnDefaultValue() {
        val key = "getInt_clearPrefs_returnDefaultValue("

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1, prefessor.getInt(key, 1))
    }

    @Test
    fun putInt_saveValue_saveSuccessfully() {
        val key = "putInt_saveValue_saveSuccessfully"

        // when
        prefessor.edit().putInt(key, 30)
        prefessor.edit().apply()

        // then
        assertEquals(30, prefessor.getInt(key, 10))
    }

    @Test
    fun putInt_saveValue_notSaveValue() {
        val key = "putInt_saveValue_notSaveValue"

        // when
        prefessor.edit().putInt(key, 7)

        // then
        assertEquals(1, prefessor.getInt(key, 1))
    }

    @Test
    fun getLong_clearPrefs_returnDefaultValue() {
        val key = "getLong_clearPrefs_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1L, prefessor.getLong(key, 1L))
    }

    @Test
    fun putLong_saveValue_saveSuccessfully() {
        val key = "putLong_saveValue_saveSuccessfully"

        // when
        prefessor.edit().putLong(key, 30L)
        prefessor.edit().apply()

        // then
        assertEquals(30L, prefessor.getLong(key, 10L))
    }

    @Test
    fun putLong_saveValue_notSaveValue() {
        val key = "putLong_saveValue_notSaveValue"

        // when
        prefessor.edit().putLong(key, 7L)

        // then
        assertEquals(1L, prefessor.getLong(key, 1L))
    }

    @Test
    fun getString_clearPrefs_returnDefaultValue() {
        val key = "getString_clearPrefs_returnDefaultValue"

        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals("default", prefessor.getString(key, "default"))
    }

    @Test
    fun putString_putValue_saveSuccessfully() {
        val key = "putString_putValue_saveSuccessfully"

        // when
        prefessor.edit().putString(key, "test_value")
        prefessor.edit().apply()

        // then
        assertEquals("test_value", prefessor.getString(key, "default"))
    }

    @Test
    fun putString_doNotApply_notSaveValue() {
        val key = "putString_saveValue_notSaveValue"

        // when
        prefessor.edit().putString(key, "test_value")

        // then
        assertEquals("default",
                prefessor.getString(key, "default"))
    }

    @Test
    fun remove_putValueAndRemoveValue_clearSuccess() {
        val key = "remove_putValueAndRemoveValue_clearSuccess"

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
    fun clean_putValueAndClearValue_clearSuccess() {
        val key = "clean_putValueAndClearValue_clearSuccess"

        // when
        prefessor.edit().putBoolean(key, true)
        prefessor.edit().apply()

        // and
        prefessor.edit().clear()
        prefessor.edit().apply()

        // than
        assertFalse { prefessor.getBoolean(key, false) }
    }
}