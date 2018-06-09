package digital.wup.prefessor.utils

import android.support.test.InstrumentationRegistry
import digital.wup.prefessor.Prefessor

actual sealed class PrefessorProvider {
    actual companion object {
        private val prefessor by lazy {
            Prefessor.create(InstrumentationRegistry.getTargetContext())
        }

        actual fun get(): Prefessor {
            return prefessor
        }
    }
}