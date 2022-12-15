package com.eshc.dolhareubab.data.source.remote.api

import com.eshc.dolhareubab.data.model.AddressInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {

    @GET("v2/local/geo/coord2address.json")
    suspend fun getAddress(
        @Query("x") longitude : String ,
        @Query("y") latitude : String

    ) : Response<AddressInfo>
}