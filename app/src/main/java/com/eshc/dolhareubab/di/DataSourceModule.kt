package com.eshc.dolhareubab.di

import com.eshc.dolhareubab.data.source.FoodDataSource
import com.eshc.dolhareubab.data.source.UserDataSource
import com.eshc.dolhareubab.data.source.remote.FoodDataSourceImpl
import com.eshc.dolhareubab.data.source.remote.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserDataSource(
        programDataSource : UserDataSourceImpl
    ) : UserDataSource

    @Binds
    abstract fun bindFoodDataSource(
        programDataSource : FoodDataSourceImpl
    ) : FoodDataSource
}