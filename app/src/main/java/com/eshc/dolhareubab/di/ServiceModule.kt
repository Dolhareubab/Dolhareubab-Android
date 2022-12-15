package com.eshc.dolhareubab.di

import com.eshc.dolhareubab.data.source.remote.api.DolhareubabService
import com.eshc.dolhareubab.data.source.remote.api.KakaoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideKakaoService(
        @KakaoRetrofit retrofit: Retrofit
    ) : KakaoService {
        return retrofit.create(KakaoService::class.java)
    }

    @Provides
    @Singleton
    fun provideDolhareubabService(
        @DolhareubabRetrofit retrofit: Retrofit
    ) : DolhareubabService {
        return retrofit.create(DolhareubabService::class.java)
    }
}