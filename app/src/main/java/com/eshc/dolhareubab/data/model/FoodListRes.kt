package com.eshc.dolhareubab.data.model

data class FoodListRes(
    val foodDetailListNotMine : List<Food>,
    val foodDetailList : List<Food>,
    val foodDetailListAll : List<Food>
)
