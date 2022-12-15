package com.eshc.dolhareubab.data.model

data class SortedFoodListRes(
    val foodDetailListNotMine : List<Food>,
    val foodDetailExpiration : List<Food>,
    val foodDetaillocation : List<Food>
)
