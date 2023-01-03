package com.eshc.dolhareubab.ui.gallery

import com.eshc.dolhareubab.util.MediaImage

data class GalleryPhotoUiModel(
    val mediaImage: MediaImage,
    var selected : Boolean = false
)