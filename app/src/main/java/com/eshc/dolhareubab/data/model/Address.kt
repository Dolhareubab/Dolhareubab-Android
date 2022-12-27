package com.eshc.dolhareubab.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address_name")
    val addressName : String,
    @SerializedName("region_1depth_name")
    val region1depthName : String,
    @SerializedName("region_2depth_name")
    val region2depthName : String,
    @SerializedName("region_3depth_name")
    val region3depthName : String,
    @SerializedName("mountain_yn")
    val mountainYn : String,
    @SerializedName("main_address_no")
    val mainAddressNo : String,
    @SerializedName("sub_address_no")
    val subAddressNo : String
)
