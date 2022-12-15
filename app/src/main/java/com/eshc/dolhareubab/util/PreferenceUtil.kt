package com.eshc.dolhareubab.util

import androidx.preference.PreferenceManager
import com.eshc.dolhareubab.App

object PreferenceUtil {
    private const val USER_ID = "user_id"

    private val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(App.instance)
    }
    var userId
        get() = prefs.getInt(USER_ID,-1)
        set(value) = prefs.edit().putInt(USER_ID, value).apply()
}