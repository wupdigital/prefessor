package digital.wup.prefessor

/**
 * Class for accessing and modifying preference data. For any particular set of preferences,
 * there is a single instance of this class that all clients share.
 * Modifications to the preferences must go through an [PrefessorEditor] object to ensure the preference values
 * remain in a consistent state and control when they are committed to storage.
 * Objects that are returned from the various get methods must be treated as immutable by the application.
 */
expect class Prefessor {

    companion object {
        /**
         * Creates a new [Prefessor] instance.
         * @return A new [Prefessor] instance
         */
        fun create(): Prefessor
    }

    /**
     * Checks whether the preferences contains a preference.
     * @param key The name of the preference to check.
     * @return Returns true if the preference exists in the preferences, otherwise false.
     */
    fun contains(key: String): Boolean

    /**
     * Retrieve a boolean value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    fun getBoolean(key: String, defValue: Boolean): Boolean

    /**
     * Retrieve a float value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    fun getFloat(key: String, defValue: Float): Float

    /**
     * Retrieve an int value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    fun getInt(key: String, defValue: Int): Int

    /**
     * Retrieve a long value from the preferences.
     * @param key The name of the preference to retrieve.
     * @param defValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.
     */
    fun getLong(key: String, defValue: Long): Long

    /**
     * Retrieve a string value from the preferences.
     * @param defValue Value to return if this preference does not exist.
     * @param key The name of the preference to retrieve.
     */
    fun getString(key: String, defValue: String? = null): String?

    /**
     * Create a new [PrefessorEditor] for these preferences,
     * through which you can make modifications to the data in the preferences and atomically commit
     * those changes back to the [Prefessor] object.
     */
    fun edit(): PrefessorEditor
}

/**
 * Class used for modifying values in a [Prefessor] object.
 * All changes you make in an editor are batched,
 * and not copied back to the original SharedPreferences until you call [apply()][PrefessorEditor.apply]
 */
expect class PrefessorEditor {

    /**
     * Set a boolean value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putBoolean(key: String, value: Boolean): PrefessorEditor

    /**
     * Set a float value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putFloat(key: String, value: Float): PrefessorEditor

    /**
     * Set an int value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putInt(key: String, value: Int): PrefessorEditor

    /**
     * Set a long value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putLong(key: String, value: Long): PrefessorEditor

    /**
     * Set a string value in the preferences editor, to be written back once [apply()][apply] are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putString(key: String, value: String?): PrefessorEditor

    /**
     * Mark in the editor that a preference value should be removed, which will be done in the actual preferences once [apply()][apply] is called.
     * @param key The name of the preference to remove.
     */
    fun remove(key: String): PrefessorEditor

    /**
     * Mark in the editor to remove all values from the preferences. Once apply is called, the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first, regardless of whether you called clear before or after put methods on this editor.
     */
    fun clear(): PrefessorEditor

    /**
     * Commit your preferences changes back from this [PrefessorEditor] to the [Prefessor] object it is editing.
     */
    fun apply()
}

/**
 * Allows editing of this preference instance with a call to [apply()][PrefessorEditor.apply]
 * to persist the changes.
 * ```
 * prefs.edit {
 *     putString("key", value)
 * }
 * ```
 */
inline fun Prefessor.edit(action: PrefessorEditor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}
