package com.eshc.dolhareubab.data.model

data class SharedFood(
    val id: Int,
    val item: String,
    val purchaseDate: String,
    val expirationTime: String,
    val talkUrl: String,
    val lati: String,
    val longti: String,
    val imageUri: String
)
