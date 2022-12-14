package com.eshc.dolhareubab.ui.share

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eshc.dolhareubab.databinding.FragmentShareBinding
import com.eshc.dolhareubab.ui.MainViewModel
import com.eshc.dolhareubab.ui.gallery.GalleryActivity
import com.eshc.dolhareubab.util.ImageUtil.getImagePath
import com.eshc.dolhareubab.util.MediaImageItem
import com.eshc.dolhareubab.util.PermissionType
import com.eshc.dolhareubab.util.PermissionUtil
import com.eshc.dolhareubab.util.READ_PERMISSIONS_REQUEST_CODE
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class ShareFragment : Fragment() {
    private var _binding: FragmentShareBinding? = null
    private val binding get() = _binding

    private val mainViewModel : MainViewModel by activityViewModels()
    private val shareViewModel : ShareViewModel by viewModels()

    private val permissions = listOf(
        PermissionType.WRITE_EXTERNAL_STORAGE,
        PermissionType.READ_EXTERNAL_STORAGE
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShareBinding.inflate(inflater)
        return binding?.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.mainViewModel = mainViewModel
        binding?.shareViewModel = shareViewModel

        binding?.ivImage?.setOnClickListener {
            checkPermission()
        }

        binding?.tvFoodPurchase?.setOnClickListener {
            getDatePicker{
                shareViewModel.foodPurchase.value = it
            }
        }

        binding?.tvFoodExpiration?.setOnClickListener {
            getDatePicker {
                shareViewModel.foodExpiration.value = it
            }
        }

        binding?.btnOk?.setOnClickListener {
            shareViewModel.addFood(
                lati = mainViewModel.myLatitude,
                longti = mainViewModel.myLongitude
            )

        }

        initObserver()
    }

    private fun initObserver() {
        shareViewModel.foodImage.observe(viewLifecycleOwner){
            if(it != null){
                binding?.ivImage?.setImageURI(it.uri)
                binding?.tvImage?.visibility = View.INVISIBLE
                isValidButton()
                //shareViewModel.foodImage.value = null
            }
        }
        shareViewModel.foodName.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()){
                isValidButton()
            }
        }
        shareViewModel.foodKakaoLink.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty()){
                isValidButton()
            }
        }
        shareViewModel.foodPurchase.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty()){
                binding?.tvFoodPurchase?.text = it
                isValidButton()
            }
        }

        shareViewModel.foodExpiration.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty()){
                binding?.tvFoodExpiration?.text = it
                isValidButton()
            }
        }

        shareViewModel.shareUiState.observe(viewLifecycleOwner){
            binding?.btnOk?.isEnabled = it
        }

        shareViewModel.submitState.observe(viewLifecycleOwner){
            if(it != null && it) {
                Toast.makeText(requireContext(),"????????? ?????????????????????!",Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == READ_PERMISSIONS_REQUEST_CODE){
            startActivityForResult(
                Intent(
                    requireActivity(),
                    GalleryActivity::class.java
                ),2000
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        when (requestCode) {
            2000 -> {
                val mediaImageItem = data?.getParcelableExtra<MediaImageItem>("mediaImage")

                if (mediaImageItem != null && mediaImageItem.images.size > 0) {
                    shareViewModel.foodImage.value = FoodImage(getImagePath(requireContext(),mediaImageItem.images[0].contentUri),mediaImageItem.images[0].contentUri)

                } else {
                    Toast.makeText(requireContext(), "????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(requireContext(), "????????? ???????????? ???????????????.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigatePhotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 2000)
    }

    private fun checkPermission() {
        if(PermissionUtil.checkPermission(
                permissions,
                requireActivity(),
                READ_PERMISSIONS_REQUEST_CODE
            )) {
//                    navigatePhotos()
            startActivityForResult(
                Intent(
                    requireActivity(),
                    GalleryActivity::class.java
                ),2000
            )
        }

    }


    private fun isValidButton()  {
        shareViewModel.shareUiState.value = (shareViewModel.foodImage.value != null
                && !shareViewModel.foodName.value.isNullOrEmpty()
                && !shareViewModel.foodKakaoLink.value.isNullOrEmpty()
                && !shareViewModel.foodPurchase.value.isNullOrEmpty()
                && !shareViewModel.foodExpiration.value.isNullOrEmpty())
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getDatePicker(onSelect: (String)->Unit){
        val calendar = Calendar.getInstance()

        DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                onSelect("$p1-${p2+1}-$p3")
            }

        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}