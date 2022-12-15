package com.eshc.dolhareubab.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.source.remote.api.KakaoService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val kakaoService: KakaoService
) : ViewModel() {
    var myLatitude : String = ""
    var myLongitude : String = ""
    val address = MutableLiveData<String>("")

    fun getAddress(longitude : String, latitude : String) {
        myLatitude = latitude
        myLongitude = longitude
        viewModelScope.launch {
            val response = kakaoService.getAddress(longitude, latitude)
            response.body()?.documents?.get(0)?.roadAddress?.let {
                address.value = "${it.region1depthName} ${it.region2depthName} ${it.region3depthName}"
            }
        }

    }
}