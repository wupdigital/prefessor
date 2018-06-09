package digital.wup.prefessor

import digital.wup.prefessor.utils.PrefessorProvider
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
        prefessor = PrefessorProvider.get()
        prefessor.edit().clear()
        prefessor.edit().apply()
    }

    @Test
    fun getBoolean_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()

        // then
        assertTrue(prefessor.getBoolean(KEY, true))
    }

    @Test
    fun putBoolean_putTrue_hasBeenSaved() {
        // when
        prefessor.edit().putBoolean(KEY, true)
        prefessor.edit().apply()

        // then
        assertTrue(prefessor.getBoolean(KEY, false))
    }

    @Test
    fun putBoolean_putTrue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putBoolean("putBoolean_putTrue_doNotSaveWithoutApply", true)

        // then
        assertFalse(prefessor.getBoolean("putBoolean_putTrue_doNotSaveWithoutApply", false))
    }

    @Test
    fun getFloat_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1.0f, prefessor.getFloat(KEY, 1.0f))
    }

    @Test
    fun putFloat_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putFloat(KEY, 30.0f)
        prefessor.edit().apply()

        // then
        assertEquals(30.0f, prefessor.getFloat(KEY, 10.0f))
    }

    @Test
    fun putFloat_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putFloat("putFloat_saveValue_doNotSaveWithoutApply", 7.0f)

        // then
        assertEquals(1.0f, prefessor.getFloat("putFloat_saveValue_doNotSaveWithoutApply", 1.0f))
    }

    @Test
    fun getInt_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1, prefessor.getInt(KEY, 1))
    }

    @Test
    fun putInt_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putInt(KEY, 30)
        prefessor.edit().apply()

        // then
        assertEquals(30, prefessor.getInt(KEY, 10))
    }

    @Test
    fun putInt_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putInt("putInt_saveValue_doNotSaveWithoutApply", 7)

        // then
        assertEquals(1, prefessor.getInt("putInt_saveValue_doNotSaveWithoutApply", 1))
    }

    @Test
    fun getLong_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals(1L, prefessor.getLong(KEY, 1L))
    }

    @Test
    fun putLong_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putLong(KEY, 30L)
        prefessor.edit().apply()

        // then
        assertEquals(30L, prefessor.getLong(KEY, 10L))
    }

    @Test
    fun putLong_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putLong("putLong_saveValue_doNotSaveWithoutApply", 7L)

        // then
        assertEquals(1L, prefessor.getLong("putLong_saveValue_doNotSaveWithoutApply", 1L))
    }

    @Test
    fun getString_afterClear_returnsWithDefaultValue() {
        // when
        prefessor.edit().clear()
        prefessor.edit().apply()
        // then
        assertEquals("default", prefessor.getString(KEY, "default"))
    }

    @Test
    fun putString_saveValue_hasBeenSaved() {
        // when
        prefessor.edit().putString(KEY, "test_value")
        prefessor.edit().apply()

        // then
        assertEquals("test_value", prefessor.getString(KEY, "default"))
    }

    @Test
    fun putString_saveValue_doNotSaveWithoutApply() {
        // when
        prefessor.edit().putString("putString_saveValue_doNotSaveWithoutApply", "test_value")

        // then
        assertEquals("default",
                prefessor.getString("putString_saveValue_doNotSaveWithoutApply", "default"))
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