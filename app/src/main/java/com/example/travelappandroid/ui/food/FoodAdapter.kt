package com.example.travelappandroid.ui.food

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.databinding.ItemFoodBinding

class FoodAdapter(private var items: List<Food>? = null, private val onItemClick: ((Food) -> Unit)? = null) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Food?) {
            binding.tvFoodName.text = item?.name
            Glide.with(binding.imgFood).load(item?.thumbnail).into(binding.imgFood)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount() = items?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Food>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

}
