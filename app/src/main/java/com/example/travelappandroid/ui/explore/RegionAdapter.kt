package com.example.travelappandroid.ui.explore

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.R
import com.example.travelappandroid.data.model.RegionItem
import com.example.travelappandroid.databinding.ItemRegionBinding

class RegionAdapter(private val items: List<RegionItem>?, private val onItemClick: ((RegionItem?) -> Unit)? = null) : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {
    private var selectedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegionViewHolder {
        val binding = ItemRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RegionViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: RegionViewHolder,
        position: Int
    ) {
        val item = items?.get(position)
        val isSelected = position == selectedPosition

        holder.bind(item, isSelected)

        holder.itemView.setOnClickListener {
            selectedPosition = if (selectedPosition == position) -1 else position
            notifyDataSetChanged()
            onItemClick?.invoke(items?.get(position))
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class RegionViewHolder(val binding: ItemRegionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RegionItem?, isSelected: Boolean) {
            binding.tvRegionName.text = item?.title
            Glide.with(binding.imgRegion).load(item?.imageUrl).into(binding.imgRegion)

            if (isSelected) {
                binding.cardRegion.strokeColor = binding.root.context.getColor(R.color.primary)
                binding.cardRegion.strokeWidth = 6
                binding.overlayView.setBackgroundColor("#332C7EAE".toColorInt())
            } else {
                binding.cardRegion.strokeColor = Color.TRANSPARENT
                binding.cardRegion.strokeWidth = 0
                binding.overlayView.setBackgroundColor("#55000000".toColorInt())
            }
        }
    }
}