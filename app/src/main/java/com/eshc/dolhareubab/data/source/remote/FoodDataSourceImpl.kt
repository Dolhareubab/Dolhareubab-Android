package com.eshc.dolhareubab.data.source.remote

import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.data.model.FoodListRes
import com.eshc.dolhareubab.data.model.SortedFoodListRes
import com.eshc.dolhareubab.data.source.FoodDataSource
import com.eshc.dolhareubab.data.source.remote.api.DolhareubabService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class FoodDataSourceImpl @Inject constructor(
    private val dolhareubabService: DolhareubabService
) : FoodDataSource {
    override suspend fun addFood(
        id: Int,
        item: String,
        purchaseDate: String,
        expirationTime: String,
        talkUrl: String,
        lati: String,
        longti: String,
        imageUri: String
    ): Result<Boolean> {
        try {
            val file = File(imageUri)
            val requestBody: RequestBody =
                RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)

            val response = dolhareubabService.addFood(
                id = MultipartBody.Part.createFormData("id", id.toString()),
                item = MultipartBody.Part.createFormData("item", item),
                buyTime = MultipartBody.Part.createFormData("buyTime", purchaseDate),
                expirationTime = MultipartBody.Part.createFormData(
                    "expirationTime",
                    expirationTime
                ),
                talkUrl = MultipartBody.Part.createFormData("talkUrl", talkUrl),
                lati = MultipartBody.Part.createFormData("lati", lati),
                longti = MultipartBody.Part.createFormData("longti", longti),
                files = MultipartBody.Part.createFormData("files", file.name, requestBody) // image
            )

            if(response.isSuccessful)
                return Result.success(true)
            else return Result.success(false)

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getFoods(userId: Int): Result<FoodListRes> {
        val response = dolhareubabService.getFoods(userId)
        try {
            return Result.success(
                response.body() ?: FoodListRes(
                    emptyList(), emptyList(),
                    emptyList()
                )
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getSortedFoods(
        userId: Int,
        lati: String,
        longti: String
    ): Result<SortedFoodListRes> {
        val response = dolhareubabService.getSortedFoods(userId, lati, longti)
        try {
            return Result.success(
                response.body() ?: SortedFoodListRes(
                    emptyList(), emptyList(),
                    emptyList()
                )
            )
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getFoodById(foodId: Int): Result<Food> {
        val response = dolhareubabService.getFoodById(foodId)
        try {
            if (response.body() != null) {
                return Result.success(response.body()!!.userDetail)
            } else {
                return Result.failure(Exception())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun checkFood(foodId: Int): Result<Food> {
        val response = dolhareubabService.checkFood(foodId)
        try {
            if (response.body() != null) {
                return Result.success(response.body()!!.userDetail)
            } else {
                return Result.failure(Exception())
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}