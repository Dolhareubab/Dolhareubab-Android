package com.eshc.dolhareubab.data.repository

import com.eshc.dolhareubab.data.source.remote.FoodDataSourceImpl
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDataSource: FoodDataSourceImpl
) : FoodRepository {

}