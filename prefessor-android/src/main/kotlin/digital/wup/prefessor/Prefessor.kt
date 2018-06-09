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
            val prefs = PreferenceManager.getDefaultSharedPreferences(ContextProvider.get())

            return Prefessor(prefs)
        }

        @JvmStatic
        actual fun create(space: String): Prefessor {
            val context = ContextProvider.get()
            val prefs = context.getSharedPreferences(space, Context.MODE_PRIVATE)

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

actual class PrefessorEditor internal constructor(private val editor: SharedPreferences.Editor) {

    actual fun apply() {
        editor.apply()
    }

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
}
