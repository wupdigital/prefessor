package digital.wup.prefessor

actual class Prefessor {

    actual companion object {
        actual fun create(): Prefessor {
            return Prefessor()
        }

        actual fun create(space: String): Prefessor {
            return Prefessor()
        }
    }

    actual fun getBoolean(key: String): Boolean {
        return false
    }

    actual fun edit(): PrefessorEditor {
        return PrefessorEditor()
    }
}

actual class PrefessorEditor {
    actual fun putBoolean(key: String, value: Boolean) {
    }

    actual fun apply() {
    }
}
