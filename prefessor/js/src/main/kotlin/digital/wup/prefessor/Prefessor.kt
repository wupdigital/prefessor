package digital.wup.prefessor

import kotlin.browser.localStorage
import kotlin.collections.mutableListOf
import org.w3c.dom.Storage

/**
 * Class for accessing and modifying preference data. For any particular set of preferences,
 * there is a single instance of this class that all clients share.
 * Modifications to the preferences must go through an [PrefessorEditor] object to ensure the preference values
 * remain in a consistent state and control when they are committed to storage.
 * Objects that are returned from the various get methods must be treated as immutable by the application.
 */
actual class Prefessor private constructor(private val storage: Storage = localStorage) {

    actual companion object {

        /**
         * Creates a new [Prefessor] instance.
         * @return A new [Prefessor] instance
         */
        actual fun create(): Prefessor {
            return Prefessor()
        }
    }

    /**
     * Checks whether the preferences contains a preference.
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences, otherwise false.
     */
    actual fun contains(key: String): Boolean {
        return storage.getItem(key) != null
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getBoolean(key: String, defValue: Boolean): Boolean {
        return storage.getItem(key)?.toBoolean() ?: defValue
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getFloat(key: String, defValue: Float): Float {
        return storage.getItem(key)?.toFloat() ?: defValue

    }

    /**
     * Retrieve an int value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getInt(key: String, defValue: Int): Int {
        return storage.getItem(key)?.toInt() ?: defValue
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getLong(key: String, defValue: Long): Long {
        return storage.getItem(key)?.toLong() ?: run {
            defValue
        }
    }

    /**
     * Retrieve a string value from the preferences.
     * @param defValue Value to return if this preference does not exist.
     * @param key The name of the preference to retrieve.
     */
    actual fun getString(key: String, defValue: String?): String? {
        return storage.getItem(key) ?: defValue
    }

    /**
     * Create a new [PrefessorEditor] for these preferences,
     * through which you can make modifications to the data in the preferences and atomically commit
     * those changes back to the [Prefessor] object.
     */
    actual fun edit(): PrefessorEditor {
        return PrefessorEditor(storage)
    }
}

/**
 * Class used for modifying values in a [Prefessor] object.
 * All changes you make in an editor are batched,
 * and not copied back to the original SharedPreferences until you call [apply()][PrefessorEditor.apply]
 */
actual class PrefessorEditor internal constructor(private val storage: Storage) {

    private val pending = mutableListOf<() -> Unit>()
    private var clear = false

    /**
     * Set a boolean value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putBoolean(key: String, value: Boolean): PrefessorEditor {
        pending.add {
            storage.setItem(key, value.toString())
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
            storage.setItem(key, value.toString())
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
            storage.setItem(key, value.toString())
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
            storage.setItem(key, value.toString())
        }
        return this
    }

    /**
     * Set a string value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putString(key: String, value: String?): PrefessorEditor {
        pending.add {
            if (value == null) {
                storage.removeItem(key)
            } else {
                storage.setItem(key, value)
            }
        }
        return this
    }

    /**
     * Mark in the editor that a preference value should be removed,
     * which will be done in the actual preferences once [apply()][apply] is called.
     * @param key The name of the preference to remove.
     */
    actual fun remove(key: String): PrefessorEditor {
        pending.add {
            storage.removeItem(key)
        }
        return this
    }

    /**
     * Mark in the editor to remove all values from the preferences. Once commit is called,
     * the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first,
     * regardless of whether you called clear before or after put methods on this editor.
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
            storage.clear()
            clear = false
        }

        pending.forEach {
            it()
        }
        // clear pendings after apply
        pending.removeAll { true }
    }
}
