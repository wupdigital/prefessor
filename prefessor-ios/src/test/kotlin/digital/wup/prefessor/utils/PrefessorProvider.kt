package digital.wup.prefessor.utils

import digital.wup.prefessor.Prefessor

actual sealed class PrefessorProvider {

    actual companion object {

        private val prefessor by lazy {
            Prefessor.create()
        }

        actual fun get(): Prefessor {
            return prefessor
        }
    }
}