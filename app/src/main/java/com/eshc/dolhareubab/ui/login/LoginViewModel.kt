package com.eshc.dolhareubab.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepositoryImpl
) : ViewModel() {
    val name = MutableLiveData<String>()
    val phone = MutableLiveData<String>()

    val loginState = MutableLiveData(false)


    fun register(){
        viewModelScope.launch {
            try {
                val user = userRepository.addUser(name.value.toString(), phone.value.toString()).getOrThrow()
                userRepository.setUserId(user.id)
                loginState.value = true
            } catch (e: Exception){

            }
        }
    }
    init {
        if(userRepository.getUserId() != -1) {
            loginState.value = true
        }
    }


}