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

    actual fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    actual fun edit(): PrefessorEditor {
        return editor
    }
}

actual class PrefessorEditor internal constructor(private val editor: SharedPreferences.Editor) {

    actual fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
    }

    actual fun apply() {
        editor.apply()
    }
}
