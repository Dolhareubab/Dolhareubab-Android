package com.eshc.dolhareubab.data.model

data class Food(
    val id : Int,
    val item : String,
    val buyTime : String,
    val expirationTime : String,
    val savedPath : String,
    val user : User,
    var doneCk : Boolean = false,
    val talkUrl : String,
    val createTime : String,
    val lati : String,
    val longti : String,
    val foodDetailIdx : Int,
    var state : String = "",
    val distance : String = ""
) {
    val imageUrl : String
        get() = "https://stoneserver-rcidr.run.goorm.io" + savedPath

    val filteredBuyTime : String
        get() = buyTime.split("T").first()

    val filteredExpirationTIme : String
        get() = expirationTime.split("T").first()

}
