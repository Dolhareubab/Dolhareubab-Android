package com.eshc.dolhareubab.data.model

import com.google.gson.annotations.SerializedName

data class RoadAddress(
    @SerializedName("address_name")
    val addressName : String,
    @SerializedName("region_1depth_name")
    val region1depthName : String,
    @SerializedName("region_2depth_name")
    val region2depthName : String,
    @SerializedName("region_3depth_name")
    val region3depthName : String,
    @SerializedName("road_name")
    val roadName : String,
    @SerializedName("underground_yn")
    val undergroundYn : String,
    @SerializedName("main_building_no")
    val mainBuildingNo : String,
    @SerializedName("sub_building_no")
    val subBuildingNo : String,
    @SerializedName("building_name")
    val buildingName : String,
    @SerializedName("zone_no")
    val zoneNo : String,
)
