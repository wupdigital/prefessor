package digital.wup.prefessor.utils

import digital.wup.prefessor.Prefessor

expect sealed class PrefessorProvider {

    companion object {
        fun get(): Prefessor
    }
}