package com.eshc.dolhareubab.ui

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.databinding.ActivityMainBinding
import com.eshc.dolhareubab.util.LOCATION_PERMISSIONS_REQUEST_CODE
import com.eshc.dolhareubab.util.PermissionType
import com.eshc.dolhareubab.util.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    private val viewModel: MainViewModel by viewModels()

    private val permissions = listOf(PermissionType.ACCESS_FINE_LOCATION, PermissionType.ACCESS_COARSE_LOCATION)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding?.lifecycleOwner = this

        if(PermissionUtil.checkPermission(permissions, this, LOCATION_PERMISSIONS_REQUEST_CODE)){
            getLocation()
        }

        initViews()
    }

    private fun initViews(){
        binding?.ivMypage?.setOnClickListener {
            binding?.fcvMain?.let {
                if(findNavController(it.id).currentDestination?.id != R.id.fragment_mypage)
                    findNavController(it.id).navigate(R.id.fragment_mypage)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lo = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        viewModel.getAddress(lo?.longitude.toString(), lo?.latitude.toString())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_PERMISSIONS_REQUEST_CODE)
            getLocation()
    }
}