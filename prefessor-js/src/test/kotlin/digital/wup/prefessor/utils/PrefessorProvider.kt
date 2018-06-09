package digital.wup.prefessor.utils

import digital.wup.prefessor.Prefessor

@JsModule("mock-local-storage")
@JsNonModule()
/*
 * TODO I don't know what is the correct type of localStorage
 * I have to learn more about Kotlin/Js interop
 */
external val localStorage: () -> Unit

actual sealed class PrefessorProvider {
    actual companion object {
        private val prefessor by lazy {
            // Without this line, mock-local-storage do not replace the browser.localStorage
            localStorage
            Prefessor.create()
        }

        actual fun get(): Prefessor {
            return prefessor
        }
    }
}