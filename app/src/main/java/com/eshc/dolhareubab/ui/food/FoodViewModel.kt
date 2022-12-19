package com.eshc.dolhareubab.ui.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.SortedFoodListRes
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.data.repository.FoodRepository
import com.eshc.dolhareubab.data.repository.UserRepository
import com.eshc.dolhareubab.data.source.FoodDataSource
import com.eshc.dolhareubab.data.source.remote.FoodDataSourceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    var rawData: SortedFoodListRes? = null
    val foods = MutableLiveData<MutableList<Food>>(mutableListOf())
    val currentSort = MutableLiveData<Sort>(Sort.LATELY)
    val loading = MutableLiveData<Boolean>(true)


    fun getFoodList(lati: String, longti: String) {
        viewModelScope.launch {
            loading.value = true
            if (rawData == null)
                try {
                    rawData = foodRepository.getSortedFoods(userRepository.getUserId(), lati, longti).getOrThrow()
                    foods.value = rawData?.foodDetailListNotMine?.toMutableList()
                    loading.value = false
                } catch (e: Exception) {
                    loading.value = false
                }
            else {
                loading.value = false
                currentSort.value?.let {
                    when(it){
                        Sort.LATELY -> {
                            foods.value = rawData?.foodDetailListNotMine?.toMutableList()
                        }
                        Sort.DISTANCE -> {
                            foods.value = rawData?.foodDetaillocation?.toMutableList()
                        }
                        Sort.EXPIRE -> {
                            foods.value = rawData?.foodDetailExpiration?.toMutableList()
                        }
                    }
                }

            }
        }
    }

}