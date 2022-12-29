package com.eshc.dolhareubab.util

enum class DistanceUnit {
    Kilometer,
    Meter
}

object DistanceUtil {
    fun getDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double,
        unit: DistanceUnit
    ): Double {
        val theta = lon1 - lon2
        var dist =
            Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(
                deg2rad(lat2)
            ) * Math.cos(deg2rad(theta))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        return when(unit){
            DistanceUnit.Kilometer -> dist * 1.609344
            DistanceUnit.Meter -> dist * 1609.344
        }
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180 / Math.PI
    }
}

