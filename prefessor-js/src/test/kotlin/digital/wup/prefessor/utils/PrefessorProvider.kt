package digital.wup.prefessor.utils

import digital.wup.prefessor.Prefessor
import digital.wup.prefessor.mock.localStorage

actual sealed class PrefessorProvider {
    actual companion object {
        private val prefessor by lazy {
            Prefessor.create(localStorage)
        }

        actual fun get(): Prefessor {
            return prefessor
        }
    }
}