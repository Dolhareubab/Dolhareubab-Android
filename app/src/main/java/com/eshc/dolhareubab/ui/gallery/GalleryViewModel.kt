package com.eshc.dolhareubab.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eshc.dolhareubab.ui.gallery.GalleryPhotoUiModel

class GalleryViewModel : ViewModel() {
    val images = MutableLiveData<List<GalleryPhotoUiModel>>(mutableListOf())

    fun selectImage(id : Long) {
        images.value = images.value?.map {
            if(it.mediaImage.id == id){
                it.copy(selected = !it.selected)
            }
            else it
        }
    }
}