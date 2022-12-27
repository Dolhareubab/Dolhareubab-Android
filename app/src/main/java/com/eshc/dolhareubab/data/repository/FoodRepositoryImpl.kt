package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.FoodListRes
import com.eshc.dolhareubab.data.model.SharedFood
import com.eshc.dolhareubab.data.model.SortedFoodListRes
import com.eshc.dolhareubab.data.source.FoodDataSource
import com.eshc.dolhareubab.data.source.remote.FoodDataSourceImpl
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDataSource: FoodDataSource
) : FoodRepository {
    override suspend fun addFood(sharedFood: SharedFood): Result<Boolean> {
        return foodDataSource.addFood(sharedFood)
    }

    override suspend fun getFoods(userId: Int): Result<FoodListRes> {
        return foodDataSource.getFoods(userId)
    }

    override suspend fun getSortedFoods(
        userId: Int,
        lati: String,
        longti: String
    ): Result<SortedFoodListRes> {
        return foodDataSource.getSortedFoods(
            userId, lati, longti
        )
    }

    override suspend fun getFoodById(foodId: Int): Result<Food> {
        return foodDataSource.getFoodById(foodId)
    }

    override suspend fun checkFood(foodId: Int): Result<Food> {
        return foodDataSource.checkFood(foodId)
    }

}