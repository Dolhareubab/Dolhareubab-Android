package com.eshc.dolhareubab.ui.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.data.repository.FoodRepository
import com.eshc.dolhareubab.data.source.FoodDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) :ViewModel() {
    val food = MutableLiveData<Food>()

    fun getFoodById(foodId : Int){
        viewModelScope.launch {
            val result = foodRepository.getFoodById(foodId)
            try {
                food.value = result.getOrThrow()
            } catch (e: Exception){

            }
        }
    }
}