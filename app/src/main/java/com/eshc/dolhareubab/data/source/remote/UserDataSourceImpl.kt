package com.eshc.dolhareubab.data.source.remote

import com.eshc.dolhareubab.data.model.AddressInfo
import com.eshc.dolhareubab.data.model.User
import com.eshc.dolhareubab.data.model.UserReq
import com.eshc.dolhareubab.data.source.UserDataSource
import com.eshc.dolhareubab.data.source.remote.api.DolhareubabService
import com.eshc.dolhareubab.data.source.remote.api.KakaoService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val dolhareubabService: DolhareubabService,
    private val kakaoService: KakaoService
) : UserDataSource {
    override suspend fun addUser(name: String, phone: String) : Result<User> {
        val response = dolhareubabService.addUser(UserReq(name,phone))
        if(response.isSuccessful && response.body() != null){
            return Result.success(response.body()!!.user)
        } else {
            return Result.failure(Exception())
        }

    }

    override suspend fun getUserAddress(longti: String, lati: String): Result<AddressInfo> {
        try {
            val response = kakaoService.getAddress(longti,lati)
            if(response.isSuccessful && response.body() != null){
                return Result.success(response.body()!!)
            }else {
                return Result.failure(Exception())
            }
        }
        catch (e :Exception){
            return Result.failure(e)
        }

    }

}