package digital.wup.prefessor

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

actual class Prefessor private constructor(private val sharedPreferences: SharedPreferences) {

    private val editor: PrefessorEditor by lazy {
        @SuppressWarnings("CommitPrefEdits")
        val prefEditor = sharedPreferences.edit()
        PrefessorEditor(prefEditor)
    }

    actual companion object {

        @JvmStatic
        actual fun create(): Prefessor {
            return create(ContextProvider.get())
        }

        internal fun create(context: Context): Prefessor {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return Prefessor(prefs)
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
        return sharedPreferences.contains(key)
    }

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    /**
     * Retrieve a float value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getFloat(key: String, defValue: Float): Float {
        return sharedPreferences.getFloat(key, defValue)
    }

    /**
     * Retrieve an int value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getInt(key: String, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    /**
     * Retrieve a long value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    actual fun getLong(key: String, defValue: Long): Long {
        return sharedPreferences.getLong(key, defValue)
    }

    /**
     * Retrieve a string value from the preferences.
     * @param defValue Value to return if this preference does not exist.
     * @param key The name of the preference to retrieve.
     */
    actual fun getString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue)
    }
}

actual class PrefessorEditor internal constructor(private val editor: SharedPreferences.Editor) {

    /**
     * Set a boolean value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
    }

    /**
     * Set a float value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value)
    }

    /**
     * Set an int value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putInt(key: String, value: Int) {
        editor.putInt(key, value)
    }

    /**
     * Set a long value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putLong(key: String, value: Long) {
        editor.putLong(key, value)
    }

    /**
     * Set a string value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    actual fun putString(key: String, value: String) {
        editor.putString(key, value)
    }

    /**
     * Mark in the editor that a preference value should be removed, which will be done in the actual preferences once {@link #commit()} is called.
     * @param key The name of the preference to remove.
     */
    actual fun remove(key: String) {
        editor.remove(key)
    }

    /**
     * Mark in the editor to remove all values from the preferences. Once commit is called, the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first, regardless of whether you called clear before or after put methods on this editor.
     */
    actual fun clear() {
        editor.clear()
    }

    actual fun apply() {
        editor.apply()
    }
}
