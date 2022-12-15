package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.data.source.remote.UserDataSourceImpl
import com.eshc.dolhareubab.util.PreferenceUtil
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource : UserDataSourceImpl
) : UserRepository {
    override fun getUserId(): Int {
        return PreferenceUtil.userId
    }

    override fun setUserId(id : Int) {
        PreferenceUtil.userId = id
    }

    override suspend fun addUser(name: String, phone: String): Result<User> {
        val result = userDataSource.addUser(name,phone)
        try {
            return Result.success(result.getOrThrow())
        } catch (e : Exception){
            return Result.failure(e)
        }
    }

}