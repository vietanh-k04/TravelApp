package com.example.travelappandroid.ui.detail.detail_place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.R
import com.example.travelappandroid.databinding.ItemDetailGalleryBinding

class GalleryAdapter(
    private val images: List<String>,
    private val onItemClick: ((String) -> Unit)? = null
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    class GalleryViewHolder(val binding: ItemDetailGalleryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemDetailGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val imageUrl = images[position]

        // Load ảnh bằng Glide
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.bg_home_image)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.imgGalleryThumb)

        // Sự kiện click (nếu cần xem ảnh to sau này)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(imageUrl)
        }
    }

    override fun getItemCount(): Int = images.size
}