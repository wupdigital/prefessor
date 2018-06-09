package digital.wup.prefessor

import android.content.Context
import android.support.annotation.RestrictTo

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