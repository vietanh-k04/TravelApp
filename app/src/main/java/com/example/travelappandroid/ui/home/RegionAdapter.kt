package com.example.travelappandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.RegionItem
import com.example.travelappandroid.databinding.ItemRegionBinding

class RegionAdapter(private val items: List<RegionItem>?, private val onItemClick: ((RegionItem) -> Unit)? = null) : RecyclerView.Adapter<RegionAdapter.RegionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegionViewHolder {
        val binding = ItemRegionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val displayMetrics = parent.context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val itemWidth = (screenWidth / 3.2).toInt()

        binding.root.layoutParams = ViewGroup.LayoutParams(
            itemWidth,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return RegionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RegionViewHolder,
        position: Int
    ) {
        holder.bind(items?.get(position))
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class RegionViewHolder(val binding: ItemRegionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RegionItem?) {
            binding.tvRegionName.text = item?.title
            Glide.with(binding.imgRegion).load(item?.imageUrl).into(binding.imgRegion)
        }
    }
}