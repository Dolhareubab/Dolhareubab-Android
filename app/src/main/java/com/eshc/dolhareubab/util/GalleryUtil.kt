package com.eshc.dolhareubab.util

import android.content.Context
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
data class MediaImageItem(
    val images : List<MediaImage>
) : Parcelable

@Parcelize
data class MediaImage(
    val id: Long,
    val displayName: String,
    val dateTaken: Date,
    val contentUri: Uri
) : Parcelable

object GalleryUtil {

    suspend fun getAllImages(context: Context) : MutableList<MediaImage>{
        val imageList = mutableListOf<MediaImage>()

        withContext(Dispatchers.IO) {
            // 가져올 정보 선택
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_TAKEN
            )
            // 정렬 방식 선정
            val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
            // contentResolver를 사용하여 query로 이미지 정보 호출
            val query = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null, null, sortOrder
            )


            query?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val dateTakenColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
                val displayNameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val dateTaken = Date(cursor.getLong(dateTakenColumn))
                    val displayName = cursor.getString(displayNameColumn)
                    val contentUri = Uri.withAppendedPath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id.toString()
                    )

                    imageList += MediaImage(id, displayName, dateTaken, contentUri)
                }
            }
        }

        return imageList
    }


}