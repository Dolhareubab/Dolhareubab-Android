package com.eshc.dolhareubab

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
    companion object{
        lateinit var instance : App
    }
}