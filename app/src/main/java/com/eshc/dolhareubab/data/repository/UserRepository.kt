package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.model.AddressInfo
import com.eshc.dolhareubab.data.model.RoadAddress
import com.eshc.dolhareubab.data.model.User

interface UserRepository {
    fun getUserId() : Int

    fun setUserId(id : Int)

    suspend fun addUser(name : String, phone : String) : Result<User>

    suspend fun getUserAddress(longti : String, lati : String) : Result<AddressInfo>
}