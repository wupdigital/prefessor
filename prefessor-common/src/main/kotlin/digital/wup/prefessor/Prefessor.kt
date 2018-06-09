package digital.wup.prefessor

expect class Prefessor {

    companion object {
        fun create(): Prefessor

        fun create(space: String): Prefessor
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
    fun putBoolean(key: String, value: Boolean)

    fun apply()
}