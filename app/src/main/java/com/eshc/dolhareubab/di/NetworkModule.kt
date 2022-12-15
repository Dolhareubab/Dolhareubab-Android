package com.eshc.dolhareubab.di

import com.eshc.dolhareubab.data.source.remote.api.KakaoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DolhareubabRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KakaoRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @DolhareubabRetrofit
    @Provides
    @Singleton
    fun provideDolhareubabRetrofit(
        @InterceptorOkHttpClient okHttpClient: OkHttpClient,
        converter : Converter.Factory
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://stoneserver-rcidr.run.goorm.io/")
            .addConverterFactory(converter)
            .build()
    }

    @KakaoRetrofit
    @Provides
    @Singleton
    fun provideKakaoRetrofit(
        @KakaoInterceptorOkHttpClient okHttpClient: OkHttpClient,
        converter : Converter.Factory,
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }


    @InterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @KakaoInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideKakaoHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        kakaoInterceptor: KakaoInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(kakaoInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideKakaoInterceptor() : KakaoInterceptor {
        return KakaoInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }
}