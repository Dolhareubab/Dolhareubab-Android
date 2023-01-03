package com.eshc.dolhareubab.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.dolhareubab.R
import com.eshc.dolhareubab.databinding.ActivityGalleryBinding
import com.eshc.dolhareubab.ui.adapter.GalleryAdapter
import com.eshc.dolhareubab.ui.gallery.GalleryPhotoUiModel
import com.eshc.dolhareubab.util.GalleryUtil
import com.eshc.dolhareubab.util.MediaImageItem
import kotlinx.coroutines.launch

class GalleryActivity : AppCompatActivity() {

    private var _binding : ActivityGalleryBinding? = null
    private val binding get() = _binding

    private val viewModel : GalleryViewModel by viewModels()

    private val galleryAdapter : GalleryAdapter by lazy {
        GalleryAdapter(){ id ->
            viewModel.selectImage(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_gallery
        )

        initViews()
        initObserver()

        getImages()
    }

    private fun initViews(){
        binding?.let {
            initRecyclerView(it.rvGallery)
            addClickListener(it)
        }
    }

    private fun addClickListener(binding: ActivityGalleryBinding){
        binding.tvDone.setOnClickListener {
            if(viewModel.selectedImageId == null) {
                Toast.makeText(this, "사진을 선택해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                setResult(RESULT_OK,Intent().putExtra("mediaImage", MediaImageItem(
                    viewModel.images.value?.filter {
                        it.selected
                    }?.map {
                        it.mediaImage
                    } ?: emptyList()
                )))
                finish()
            }
        }
    }

    private fun initRecyclerView(recyclerView: RecyclerView){
        recyclerView.layoutManager = GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        recyclerView.adapter = galleryAdapter
    }
    private fun initObserver() {
        viewModel.images.observe(this) {
            galleryAdapter.submitList(it)
        }
    }

    private fun getImages(){
        lifecycleScope.launch{
            viewModel.images.value = GalleryUtil.getAllImages(this@GalleryActivity).map {
                GalleryPhotoUiModel(it)
            }
        }
    }
}