package com.eshc.dolhareubab.ui.mypage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.data.repository.UserRepository
import com.eshc.dolhareubab.data.source.FoodDataSource
import com.eshc.dolhareubab.data.source.remote.FoodDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val foodDataSource: FoodDataSource,
    private val userRepository: UserRepository
) : ViewModel() {

    val mypageFoods = MutableLiveData<MutableList<Food>>(mutableListOf())

    val tabState = MutableLiveData<MypageTabUiState>(MypageTabUiState.SHARE)
    val loading = MutableLiveData<Boolean>(true)

    val changedFoodIndex = MutableLiveData<Int>(-1)

    fun getFoodList(tab : MypageTabUiState){
        viewModelScope.launch {
            loading.value = true
            val result = foodDataSource.getFoods(userRepository.getUserId())
            try {
                mypageFoods.value = when(tab) {
                    MypageTabUiState.SHARE -> result.getOrThrow().foodDetailList.map {
                        it.copy(
                            state = if(it.doneCk) "나눔 끝" else "나눔 신청이 도착했어요"
                        )
                    }.toMutableList()
                    MypageTabUiState.FOOD -> result.getOrThrow().foodDetailListNotMine.map {
                        it.copy(
                            state = if(it.doneCk) "나눔 끝" else "대화중이에요"
                        )
                    }.toMutableList()
                }
                loading.value = false
            } catch (e: Exception){
                loading.value = false
            }
        }
    }

    fun updateFood(food: Food)  {
        viewModelScope.launch {
            try {
                val result = foodDataSource.checkFood(foodId = food.id)
                val targetFood = mypageFoods.value?.find {
                    it.id == food.id
                }
                targetFood?.doneCk = result.getOrThrow().doneCk
                targetFood?.state = "나눔 끝"
                changedFoodIndex.value = mypageFoods.value?.indexOf(targetFood)
            } catch (e: Exception){

            }
        }

    }
}