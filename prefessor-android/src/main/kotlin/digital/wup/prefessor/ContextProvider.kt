package digital.wup.prefessor

import android.content.Context

public class ContextProvider {
    companion object {
        fun get(): Context {

            synchronized(this) {
                if (ContextContentProvider.ctx == null) {
                    throw Exception("Contex is null")
                }

                return ContextContentProvider.ctx!!
            }
        }
    }
}