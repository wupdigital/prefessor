package digital.wup.prefessor

import kotlin.browser.localStorage
import org.w3c.dom.Storage
actual class Prefessor private constructor(private val storage: Storage = localStorage) {

    actual companion object {
        actual fun create(): Prefessor {
            return Prefessor()
        }

        /**
         * Only for testing
         */
        internal fun create(storage: Storage): Prefessor {
            return Prefessor(storage)
        }
    }

    actual fun edit(): PrefessorEditor {
        return PrefessorEditor()
    }

    /**
     * Checks whether the preferences contains a preference.
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences, otherwise false.
     */
    actual fun contains(key: String): Boolean {
        TODO("not implemented")
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getBoolean(key: String, defValue: Boolean): Boolean {
        TODO("not implemented")
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getFloat(key: String, defValue: Float): Float {
        TODO("not implemented")
    }

    /**
     * Retrieve an int value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getInt(key: String, defValue: Int): Int {
        TODO("not implemented")
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getLong(key: String, defValue: Long): Long {
        TODO("not implemented")
    }

    /**
     * Retrieve a string value from the preferences.
     * @param defValue Value to return if this preference does not exist.
     * @param key The name of the preference to retrieve.
     */
    actual fun getString(key: String, defValue: String): String {
        TODO("not implemented")
    }
}

actual class PrefessorEditor {

    /**
     * Set a boolean value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putBoolean(key: String, value: Boolean) {
        TODO("not implemented")
    }

    /**
     * Set a float value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putFloat(key: String, value: Float) {
        TODO("not implemented")
    }

    /**
     * Set an int value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putInt(key: String, value: Int) {
        TODO("not implemented")
    }

    /**
     * Set a long value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putLong(key: String, value: Long) {
        TODO("not implemented")
    }

    /**
     * Set a string value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putString(key: String, value: String) {
        TODO("not implemented")
    }

    /**
     * Mark in the editor that a preference value should be removed,
     * which will be done in the actual preferences once {@link #commit()} is called.
     * @param key The name of the preference to remove.
     */
    actual fun remove(key: String) {
        TODO("not implemented")
    }

    /**
     * Mark in the editor to remove all values from the preferences. Once commit is called,
     * the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first,
     * regardless of whether you called clear before or after put methods on this editor.
     */
    actual fun clear() {
        TODO("not implemented")
    }

    actual fun apply() {
        TODO("not implemented")
    }
}
