package com.eshc.dolhareubab.data.source

import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.FoodListRes
import com.eshc.dolhareubab.data.model.SortedFoodListRes

interface FoodDataSource {
    suspend fun addFood(
        id: Int, // userId
        item: String,
        purchaseDate: String,
        expirationTime: String,
        talkUrl: String,
        lati: String,
        longti: String,
        imageUri : String
    ) :  Result<Boolean>
    suspend fun getFoods(userId : Int) : Result<FoodListRes>

    suspend fun getSortedFoods(userId : Int,lati: String,longti : String) : Result<SortedFoodListRes>

    suspend fun getFoodById(foodId : Int) : Result<Food>

    suspend fun checkFood(foodId : Int) : Result<Food>
}