package com.eshc.dolhareubab.data.model

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("road_address")
    val roadAddress: RoadAddress?,
    val address : Address
)
