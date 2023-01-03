package com.eshc.dolhareubab.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.dolhareubab.databinding.ItemGalleryBinding
import com.eshc.dolhareubab.ui.gallery.GalleryPhotoUiModel

class GalleryAdapter(
    private val selectedListener: (Long) -> Unit
) : ListAdapter<GalleryPhotoUiModel, GalleryAdapter.GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), selectedListener
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.setClickListener(getItem(position))

    }

    override fun onBindViewHolder(
        holder: GalleryViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) super.onBindViewHolder(holder, position, payloads)
        else {
            holder.bindSelectedState(getItem(position).selected)
        }
    }

    class GalleryViewHolder(
        val binding: ItemGalleryBinding,
        private val selectedListener: (Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: GalleryPhotoUiModel) {
            binding.photo = photo
        }

        fun setClickListener(photo: GalleryPhotoUiModel) {
            binding.root.setOnClickListener {
                selectedListener(photo.mediaImage.id)
            }
        }

        fun bindSelectedState(selected: Boolean) {
            binding.ivSelected.isSelected = selected
        }

        fun bindSelectedOrder(order : Int) {

        }

    }

    private class GalleryDiffCallback : DiffUtil.ItemCallback<GalleryPhotoUiModel>() {
        override fun areItemsTheSame(
            oldItem: GalleryPhotoUiModel,
            newItem: GalleryPhotoUiModel
        ): Boolean {
            return oldItem.mediaImage.id == newItem.mediaImage.id
        }

        override fun areContentsTheSame(
            oldItem: GalleryPhotoUiModel,
            newItem: GalleryPhotoUiModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(
            oldItem: GalleryPhotoUiModel,
            newItem: GalleryPhotoUiModel
        ): Any? {
            return if (oldItem.selected != newItem.selected) true else null
        }
    }
}