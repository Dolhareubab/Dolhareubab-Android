package com.eshc.dolhareubab.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat

interface Permission {
    val requestCode: Int
    val permission: String
}

enum class PermissionType : Permission {
    ACCESS_FINE_LOCATION {
        override val requestCode: Int
            get() = 0x202
        override val permission: String
            get() = Manifest.permission.ACCESS_FINE_LOCATION
    },

    ACCESS_COARSE_LOCATION {
        override val requestCode: Int
        get() = 0x203
        override val permission: String
        get() = Manifest.permission.ACCESS_COARSE_LOCATION
    },
    READ_EXTERNAL_STORAGE {
        override val requestCode: Int
            get() = 0x204
        override val permission: String
            get() = Manifest.permission.READ_EXTERNAL_STORAGE
    },
    WRITE_EXTERNAL_STORAGE {
        override val requestCode: Int
            get() = 0x205
        override val permission: String
            get() = Manifest.permission.WRITE_EXTERNAL_STORAGE
    }
}

const val LOCATION_PERMISSIONS_REQUEST_CODE = 0x200
const val READ_PERMISSIONS_REQUEST_CODE = 0x201

object PermissionUtil {

    fun checkPermission(
        permissions: List<Permission>,
        activity: Activity,
        requestCode : Int
    ): Boolean {
        val deniedPermissions = permissions.filter { permission ->
            ContextCompat.checkSelfPermission(
                activity,
                permission.permission
            ) == PackageManager.PERMISSION_DENIED
        }
        when {
            deniedPermissions.isEmpty() -> return true
            deniedPermissions.any { permission ->
                shouldShowRequestPermissionRationale(activity, permission.permission)
            } -> {
                showDialogToGetPermission(activity, deniedPermissions,requestCode)
            }
            else -> {
                requestPermissionInActivity(activity, deniedPermissions, requestCode = requestCode)
            }
        }

        return false
    }

    private fun showDialogToGetPermission(activity: Activity, permissions: List<Permission>,requestCode:Int) {
        AlertDialog.Builder(activity)
            .setTitle("권한이 필요합니다.")
            .setMessage("앱의 기능을 사용하기위해서는 권한을 허용해야합니다.\n" + permissions.map {
                activity.applicationContext.packageManager.getPermissionInfo(it.permission,PackageManager.GET_META_DATA).loadLabel(activity.packageManager).toString()
            }.joinToString("\n"))
            .setPositiveButton("동의") { _, _ ->
                requestPermissionInActivity(activity, permissions,true,requestCode)
            }.setNegativeButton("취소") { _, _ ->

            }.create()
            .show()
    }

    private fun requestPermissionInActivity(activity: Activity, permissions: List<Permission>, isPermanent : Boolean = false,requestCode : Int) {
        if(isPermanent){ // 2번째는 경고 토스트를 띄운다.
            Toast.makeText(activity, "취소 시 설정 화면에서 권한 설정을 해야합니다.",Toast.LENGTH_LONG).show()
        }
        ActivityCompat.requestPermissions(
            activity,
            permissions.map { it.permission }.toTypedArray(),
            requestCode
        )
    }
}