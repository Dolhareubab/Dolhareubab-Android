package com.eshc.dolhareubab.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.repository.UserRepository
import com.eshc.dolhareubab.data.source.remote.api.KakaoService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {
    var myLatitude : String = ""
    var myLongitude : String = ""
    val address = MutableLiveData<String>("")

    fun getAddress(longitude : String, latitude : String) {
        myLatitude = latitude
        myLongitude = longitude
        viewModelScope.launch {
            val result = userRepository.getUserAddress(longitude, latitude)
            try {
                result.getOrThrow().let { addressInfo ->
                    if(addressInfo.documents[0].roadAddress != null){
                        addressInfo.documents[0].roadAddress?.let {
                            address.value = "${it.region1depthName} ${it.region2depthName} ${it.region3depthName}"
                        }
                    } else {
                        address.value = addressInfo.documents[0].address.addressName
                    }

                }
            } catch (e: Exception){

            }
        }
    }
}