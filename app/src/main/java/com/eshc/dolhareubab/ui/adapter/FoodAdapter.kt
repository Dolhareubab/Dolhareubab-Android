package com.eshc.dolhareubab.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eshc.dolhareubab.data.model.Food
import com.eshc.dolhareubab.databinding.ItemFoodBinding

class FoodAdapter(
    private val onClick: (Food) -> Unit
) : ListAdapter<Food, FoodAdapter.FoodViewHolder>(
    FoodDiffCallback()
) {
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            holder.setOnClickListener {
                onClick(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class FoodViewHolder(private val binding : ItemFoodBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Food){
            binding.apply {
                food = item
            }
        }
        fun setOnClickListener(onClick : (Food) -> Unit){
            binding.root.setOnClickListener {
                binding.food?.let(onClick)
            }
        }
    }

    private class FoodDiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }
}