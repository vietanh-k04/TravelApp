package com.example.travelappandroid.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Banner
import com.example.travelappandroid.databinding.ItemBannerBinding

class BannerAdapter(private var items: List<Banner>? = null) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BannerViewHolder,
        position: Int
    ) {
        holder.bind(items?.get(position))
    }

    override fun getItemCount(): Int = items?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Banner>) {
        items = newList
        notifyDataSetChanged()
    }

    class BannerViewHolder(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Banner?) {
            Glide.with(binding.imgBanner).load(item?.image).into(binding.imgBanner)
        }
    }
}