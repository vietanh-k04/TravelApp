package com.example.travelappandroid.ui.explore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.databinding.ItemExploreStaggeredBinding

class PlaceStaggeredAdapter(
    private var items: List<Place>? = null,
    private val onItemClick: ((Place) -> Unit)? = null
) : RecyclerView.Adapter<PlaceStaggeredAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemExploreStaggeredBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

    override fun getItemCount(): Int = items?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Place>?) {
        items = newItems
        notifyDataSetChanged()
    }

    class PlaceViewHolder(val binding: ItemExploreStaggeredBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(place: Place?) = with(binding) {
            place?.let { item ->
                tvExploreName.text = "${item.name} • ${item.rating ?: 0}"
                tvExploreLocation.text = item.province

                // Load ảnh bằng Glide
                Glide.with(imgExploreThumb.context)
                    .load(item.thumbnail)
                    .override(600, 800)
                    .into(imgExploreThumb)
            }
        }
    }
}