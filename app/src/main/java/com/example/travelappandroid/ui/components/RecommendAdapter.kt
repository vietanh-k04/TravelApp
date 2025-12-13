package com.example.travelappandroid.ui.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.databinding.ItemHomePlaceBinding

class RecommendAdapter(private var items: List<Place>? = null, private val onItemClick: ((Place?) -> Unit)? = null)  : RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendViewHolder {
        val binding = ItemHomePlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecommendViewHolder,
        position: Int
    ) {
        holder.bind(items?.get(position))

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(items?.get(position))
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Place>?) {
        items = newItems
        notifyDataSetChanged()
    }

    class RecommendViewHolder(val binding: ItemHomePlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place?) = with(binding) {
            tvPlaceTitle.text = place?.name
            Glide.with(imgPlace.context)
                .load(place?.thumbnail)
                .centerCrop()
                .into(imgPlace)
        }
    }
}