package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.model.User

interface UserRepository {
    fun getUserId() : Int

    fun setUserId(id : Int)

    suspend fun addUser(name : String, phone : String) : Result<User>
}