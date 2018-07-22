package digital.wup.prefessor

import kotlin.browser.localStorage
import kotlin.collections.mutableListOf
import org.w3c.dom.Storage

actual class Prefessor private constructor(private val storage: Storage = localStorage) {

    private val editor by lazy {
        PrefessorEditor(storage)
    }

    actual companion object {
        actual fun create(): Prefessor {
            return Prefessor()
        }
    }

    actual fun edit(): PrefessorEditor {
        return editor
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
        return storage.getItem(key)?.let {
            it.toBoolean()
        } ?: run {
            defValue
        }
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getFloat(key: String, defValue: Float): Float {
        return storage.getItem(key)?.let {
            it.toFloat()
        } ?: run {
            defValue
        }
    }

    /**
     * Retrieve an int value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getInt(key: String, defValue: Int): Int {
        return storage.getItem(key)?.let {
            it.toInt()
        } ?: run {
            defValue
        }
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getLong(key: String, defValue: Long): Long {
        return storage.getItem(key)?.let {
            it.toLong()
        } ?: run {
            defValue
        }
    }

    /**
     * Retrieve a string value from the preferences.
     * @param defValue Value to return if this preference does not exist.
     * @param key The name of the preference to retrieve.
     */
    actual fun getString(key: String, defValue: String): String {
        return storage.getItem(key) ?: defValue
    }
}

actual class PrefessorEditor internal constructor(private val storage: Storage) {

    private val pending = mutableListOf<() -> Unit>()
    private var clear = false

    /**
     * Set a boolean value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putBoolean(key: String, value: Boolean) {
        pending.add {
            storage.setItem(key, value.toString())
        }
    }

    /**
     * Set a float value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putFloat(key: String, value: Float) {
        pending.add {
            storage.setItem(key, value.toString())
        }
    }

    /**
     * Set an int value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putInt(key: String, value: Int) {
        pending.add {
            storage.setItem(key, value.toString())
        }
    }

    /**
     * Set a long value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putLong(key: String, value: Long) {
        pending.add {
            storage.setItem(key, value.toString())
        }
    }

    /**
     * Set a string value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putString(key: String, value: String) {
        pending.add {
            storage.setItem(key, value)
        }
    }

    /**
     * Mark in the editor that a preference value should be removed,
     * which will be done in the actual preferences once {@link #commit()} is called.
     * @param key The name of the preference to remove.
     */
    actual fun remove(key: String) {
        pending.add {
            storage.removeItem(key)
        }
    }

    /**
     * Mark in the editor to remove all values from the preferences. Once commit is called,
     * the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first,
     * regardless of whether you called clear before or after put methods on this editor.
     */
    actual fun clear() {
        clear = true
    }

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
