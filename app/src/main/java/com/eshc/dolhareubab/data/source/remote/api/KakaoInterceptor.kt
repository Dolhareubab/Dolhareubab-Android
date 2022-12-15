package com.eshc.dolhareubab.data.source.remote.api

import com.eshc.dolhareubab.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class KakaoInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder().apply {
            header("Authorization", "KakaoAK ${BuildConfig.KAKAO_REST_KEY}")
            method(original.method, original.body)
        }.build()

        return chain.proceed(request)
    }
}