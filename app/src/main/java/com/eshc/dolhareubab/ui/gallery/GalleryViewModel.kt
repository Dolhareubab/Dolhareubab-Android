package com.eshc.dolhareubab.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    val images = MutableLiveData<List<GalleryPhotoUiModel>>(mutableListOf())

    private var _selectedImageId : Long? = null
    val selectedImageId get() = _selectedImageId

    fun selectImage(id : Long) {
        if(_selectedImageId == null){
            images.value = images.value?.map {
                if(it.mediaImage.id == id){
                    it.copy(selected = !it.selected)
                }
                else it
            }
            _selectedImageId = id
        } else if(_selectedImageId == id){
            images.value = images.value?.map {
                if(it.mediaImage.id == _selectedImageId){
                    it.copy(selected = !it.selected)
                }
                else it
            }
            _selectedImageId = null
        }
        else {
            images.value = images.value?.map {
                if(it.mediaImage.id == id){
                    it.copy(selected = !it.selected)
                } else if(it.mediaImage.id == _selectedImageId){
                    it.copy(selected = !it.selected)
                }
                else it
            }
            _selectedImageId = id
        }

    }


}