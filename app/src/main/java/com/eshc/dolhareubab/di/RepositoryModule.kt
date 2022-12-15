package com.eshc.dolhareubab.di

import com.eshc.dolhareubab.data.repository.FoodRepository
import com.eshc.dolhareubab.data.repository.FoodRepositoryImpl
import com.eshc.dolhareubab.data.repository.UserRepository
import com.eshc.dolhareubab.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        UserRepository : UserRepositoryImpl
    ) : UserRepository

    @Binds
    abstract fun bindFoodRepository(
        UserRepository : FoodRepositoryImpl
    ) : FoodRepository
}