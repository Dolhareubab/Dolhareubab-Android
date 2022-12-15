package com.eshc.dolhareubab.data.source

import com.eshc.dolhareubab.data.model.FoodListRes
import com.eshc.dolhareubab.data.model.User

interface UserDataSource {
    suspend fun addUser(name : String,  phone : String) : Result<User>


}