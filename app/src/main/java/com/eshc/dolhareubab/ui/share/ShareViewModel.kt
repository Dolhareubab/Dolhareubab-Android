package com.eshc.dolhareubab.ui.share

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.model.SharedFood
import com.eshc.dolhareubab.data.repository.UserRepository
import com.eshc.dolhareubab.data.repository.UserRepositoryImpl
import com.eshc.dolhareubab.data.source.FoodDataSource
import com.eshc.dolhareubab.data.source.remote.FoodDataSourceImpl
import com.eshc.dolhareubab.data.source.remote.api.DolhareubabService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    val foodDataSource: FoodDataSourceImpl,
    val userRepository: UserRepositoryImpl
) : ViewModel() {

    val shareUiState = MutableLiveData<Boolean>(false)
    val submitState = MutableLiveData<Boolean>(null)

    val foodImage = MutableLiveData<FoodImage>()

    val foodName = MutableLiveData<String>()
    val foodPurchase = MutableLiveData<String>()
    val foodExpiration = MutableLiveData<String>()
    val foodKakaoLink = MutableLiveData<String>()

    fun addFood(lati: String, longti: String) {
        viewModelScope.launch {
            val result = foodDataSource.addFood(
                SharedFood(
                    id = userRepository.getUserId(),
                    item = foodName.value.toString(),
                    purchaseDate = foodPurchase.value.toString(),
                    expirationTime = foodExpiration.value.toString(),
                    talkUrl = foodKakaoLink.value.toString(),
                    imageUri = foodImage.value?.path.toString(),
                    lati = lati,
                    longti = longti
                )
            )
            try {
                submitState.value = result.getOrThrow()
            } catch (e: Exception) {
                submitState.value = false
            }
        }
    }
}