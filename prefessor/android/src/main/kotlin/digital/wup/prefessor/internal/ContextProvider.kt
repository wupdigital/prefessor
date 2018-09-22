package digital.wup.prefessor.internal

import android.content.Context
import android.support.annotation.RestrictTo

/**
 * @hide
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
sealed class ContextProvider {
    companion object {
        @JvmStatic
        fun get(): Context {
            synchronized(this) {
                ContextContentProvider.ctx?.let {
                    return it
                } ?: run {
                    throw IllegalStateException("Context == null")
                }
            }
        }
    }
}