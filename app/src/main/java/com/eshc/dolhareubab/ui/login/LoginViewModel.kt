package com.eshc.dolhareubab.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val nickname = MutableLiveData<String>()

    fun registerNickname(){
        // 닉네임 등록

        //if 닉네임 등록 성공..
        // 닉네임 등록 실패
    }
}