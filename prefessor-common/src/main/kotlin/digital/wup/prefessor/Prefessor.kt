package digital.wup.prefessor

expect class Prefessor {

    companion object {
        fun create(): Prefessor

        fun create(space: String): Prefessor
    }

    fun getBoolean(key: String): Boolean

    fun edit(): PrefessorEditor
}

expect class PrefessorEditor {
    fun putBoolean(key: String, value: Boolean)

    fun apply()
}