package com.eshc.dolhareubab.ui.common.ext

import android.content.Context
import kotlin.math.roundToInt

fun Int.dpToPx(context : Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}
