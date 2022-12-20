package com.eshc.dolhareubab.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

object ImageUtil {
    fun getImagePath(context: Context, uri: Uri): String? {
        var path : String? = uri.path
        var firstCursor: Cursor? = context.contentResolver?.query(uri, null, null, null, null)
        firstCursor?.moveToFirst()


        var document_id: String? = firstCursor?.getString(0)
        document_id = document_id?.substring(document_id.lastIndexOf(":") + 1)
        val cursor = context.contentResolver?.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null
        )
        firstCursor?.count?.let {
            if(it > 0 && cursor?.count == 0) {
                firstCursor?.getColumnIndex(MediaStore.Images.Media.DATA)?.let {
                    path = firstCursor?.getString(it)
                }
            }
        }
        firstCursor?.close()
        while(cursor?.moveToNext() == true) {
            cursor?.getColumnIndex(MediaStore.Images.Media.DATA)?.let {
                path = cursor?.getString(it)
            }
        }
        cursor?.close()
        return path
    }
}