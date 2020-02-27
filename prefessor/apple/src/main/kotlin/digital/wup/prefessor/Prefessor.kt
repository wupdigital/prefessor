package digital.wup.prefessor

import kotlin.collections.mutableListOf
import platform.Foundation.NSUserDefaults

/**
 * Class for accessing and modifying preference data. For any particular set of preferences,
 * there is a single instance of this class that all clients share.
 * Modifications to the preferences must go through an [PrefessorEditor] object to ensure the preference values
 * remain in a consistent state and control when they are committed to storage.
 * Objects that are returned from the various get methods must be treated as immutable by the application.
 */
actual class Prefessor private constructor(private val userDefaults: NSUserDefaults) {

    actual companion object {

        /**
         * Creates a new [Prefessor] instance.
         * @return A new [Prefessor] instance
         */
        actual fun create(): Prefessor {
            return Prefessor(NSUserDefaults.standardUserDefaults)
        }
    }

    /**
     * Checks whether the preferences contains a preference.
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences, otherwise false.
     */
    actual fun contains(key: String): Boolean {
        return userDefaults.objectForKey(key) != null
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getBoolean(key: String, defValue: Boolean): Boolean {
        return userDefaults.objectForKey(key) as Boolean? ?: defValue
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getFloat(key: String, defValue: Float): Float {
        return userDefaults.objectForKey(key) as Float? ?: defValue
    }

    /**
     * Retrieve an int value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getInt(key: String, defValue: Int): Int {
        return userDefaults.objectForKey(key) as Int? ?: defValue
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getLong(key: String, defValue: Long): Long {
        return userDefaults.objectForKey(key) as Long? ?: defValue
    }

    /**
     * Retrieve a string value from the preferences.
     * @param defValue Value to return if this preference does not exist.
     * @param key The name of the preference to retrieve.
     */
    actual fun getString(key: String, defValue: String?): String? {
        return userDefaults.objectForKey(key) as String? ?: defValue
    }

    /**
     * Create a new [PrefessorEditor] for these preferences,
     * through which you can make modifications to the data in the preferences and atomically commit
     * those changes back to the [Prefessor] object.
     */
    actual fun edit(): PrefessorEditor {
        return PrefessorEditor(userDefaults)
    }
}

/**
 * Class used for modifying values in a [Prefessor] object.
 * All changes you make in an editor are batched,
 * and not copied back to the original SharedPreferences until you call [apply()][PrefessorEditor.apply]
 */
actual class PrefessorEditor internal constructor(private val userDefaults: NSUserDefaults) {

    private val pending = mutableListOf<() -> Unit>()

    @kotlin.native.ThreadLocal
    private var clear = false

    /**
     * Set a boolean value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putBoolean(key: String, value: Boolean): PrefessorEditor {
        pending.add {
            userDefaults.setBool(value, key)
        }
        return this
    }

    /**
     * Set a float value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putFloat(key: String, value: Float): PrefessorEditor {
        pending.add {
            userDefaults.setFloat(value, key)
        }
        return this
    }

    /**
     * Set an int value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putInt(key: String, value: Int): PrefessorEditor {
        pending.add {
            userDefaults.setObject(value, key)
        }
        return this
    }

    /**
     * Set a long value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putLong(key: String, value: Long): PrefessorEditor {
        pending.add {
            userDefaults.setInteger(value, key)
        }
        return this
    }

    /**
     * Set a string value in the preferences editor, to be written back once [apply()][apply] is called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putString(key: String, value: String?): PrefessorEditor {
        pending.add {
            userDefaults.setObject(value, key)
        }
        return this
    }

    /**
     * Mark in the editor that a preference value should be removed, which will be done in the actual preferences once [apply()][apply] is called.
     * @param key The name of the preference to remove.
     */
    actual fun remove(key: String): PrefessorEditor {
        pending.add {
            userDefaults.removeObjectForKey(key)
        }
        return this
    }

    /**
     * Mark in the editor to remove all values from the preferences. Once apply is called, the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first, regardless of whether you called clear before or after put methods on this editor.
     */
    actual fun clear(): PrefessorEditor {
        clear = true
        return this
    }

    /**
     * Commit your preferences changes back from this [PrefessorEditor] to the [Prefessor] object it is editing.
     */
    actual fun apply() {

        if (clear) {
            clearAllFromUserDefauls()
            clear = false
        }

        pending.forEach {
            it()
        }
        // clear pendings after apply
        pending.removeAll { true }
    }

    private fun clearAllFromUserDefauls() {
        val dict = userDefaults.dictionaryRepresentation()
        dict.keys.forEach {
            userDefaults.removeObjectForKey(it as String)
        }
    }
}
