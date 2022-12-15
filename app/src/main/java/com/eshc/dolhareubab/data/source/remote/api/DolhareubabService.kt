package com.eshc.dolhareubab.data.source.remote.api

import com.eshc.dolhareubab.data.model.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface DolhareubabService {

    @POST("/user/select")
    suspend fun addUser(
        @Body userReq: UserReq
    ) : Response<UserRes>

    @Multipart
    @POST("/user/add-food")
    suspend fun addFood(
        @Part id : MultipartBody.Part, // userId
        @Part item : MultipartBody.Part,
        @Part buyTime : MultipartBody.Part,
        @Part expirationTime : MultipartBody.Part,
        @Part talkUrl : MultipartBody.Part,
        @Part lati : MultipartBody.Part,
        @Part longti : MultipartBody.Part,
        @Part files : MultipartBody.Part
    ) : Response<Unit>

    @GET("/user/food/detail-list")
    suspend fun getFoods(
        @Query("user") user : Int
    ) : Response<FoodListRes>

    @GET("/user/food/detail-list-sort")
    suspend fun getSortedFoods(
        @Query("user") user : Int,
        @Query("lati") lati : String,
        @Query("longti") longti : String
    ) : Response<SortedFoodListRes>

    @GET("/user/food/detail-one")
    suspend fun getFoodById(
        @Query("id") foodId : Int
    ) : Response<FoodRes>

    @GET("/user/food/detail-check")
    suspend fun checkFood(
        @Query("id") foodId : Int
    ) : Response<FoodRes>
}