package digital.wup.prefessor

expect class Prefessor {

    companion object {
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
    fun getString(key: String, defValue: String): String

    fun edit(): PrefessorEditor
}

expect class PrefessorEditor {

    /**
     * Set a boolean value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putBoolean(key: String, value: Boolean)

    /**
     * Set a float value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putFloat(key: String, value: Float)

    /**
     * Set an int value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putInt(key: String, value: Int)

    /**
     * Set a long value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putLong(key: String, value: Long)

    /**
     * Set a string value in the preferences editor, to be written back once {@link #apply()) are called.
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun putString(key: String, value: String)

    /**
     * Mark in the editor that a preference value should be removed, which will be done in the actual preferences once {@link #commit()} is called.
     * @param key The name of the preference to remove.
     */
    fun remove(key: String)

    /**
     * Mark in the editor to remove all values from the preferences. Once commit is called, the only remaining preferences will be any that you have defined in this editor.
     * Note that when committing back to the preferences, the clear is done first, regardless of whether you called clear before or after put methods on this editor.
     */
    fun clear()

    fun apply()
}