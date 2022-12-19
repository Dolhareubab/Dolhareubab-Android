package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.model.RoadAddress
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.data.source.UserDataSource
import com.eshc.dolhareubab.data.source.remote.UserDataSourceImpl
import com.eshc.dolhareubab.util.PreferenceUtil
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override fun getUserId(): Int {
        return PreferenceUtil.userId
    }

    override fun setUserId(id: Int) {
        PreferenceUtil.userId = id
    }

    override suspend fun addUser(name: String, phone: String): Result<User> {
        val result = userDataSource.addUser(name, phone)
        try {
            return Result.success(result.getOrThrow())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getUserAddress(longti: String, lati: String): Result<RoadAddress> {
        try {
            val result = userDataSource.getUserAddress(longti, lati)
            result.getOrThrow().documents.let { docus ->
                if (docus.isEmpty()) {
                    return Result.failure(Exception())
                } else {
                    return Result.success(docus[0].roadAddress)
                }
            }

        } catch (e: Exception) {
            return Result.failure(e)
        }

    }

}