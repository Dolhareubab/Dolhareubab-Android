package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.FoodListRes
import com.eshc.dolhareubab.data.model.SharedFood
import com.eshc.dolhareubab.data.model.SortedFoodListRes

interface FoodRepository {
    suspend fun addFood(
        sharedFood: SharedFood
    ) :  Result<Boolean>
    suspend fun getFoods(userId : Int) : Result<FoodListRes>

    suspend fun getSortedFoods(userId : Int, lati: String,longti : String) : Result<SortedFoodListRes>

    suspend fun getFoodById(foodId : Int) : Result<Food>

    suspend fun checkFood(foodId : Int) : Result<Food>
}