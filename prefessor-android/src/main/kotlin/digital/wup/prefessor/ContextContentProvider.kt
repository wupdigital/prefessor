package digital.wup.prefessor

import android.content.ContentProvider
import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

open class ContextContentProvider : ContentProvider() {

    companion object {
        @SuppressWarnings("StaticFieldLeak")
        internal var ctx: Context? = null
    }

    override fun onCreate(): Boolean {
        ctx = context
        return true
    }
    override fun query(
        uri: Uri?,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        return null
    }
    override fun getType(uri: Uri?): String? {
        return null
    }
    override fun insert(
        uri: Uri?,
        values: ContentValues?
    ): Uri? {
        return null
    }
    override fun delete(
        uri: Uri?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
    override fun update(
        uri: Uri?,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}