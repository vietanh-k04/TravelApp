package com.example.travelappandroid.ui.components

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.databinding.ItemHomePlaceBinding

class TrendingAdapter(private var items: List<Place>? = null, private val onItemClick: ((Place?) -> Unit)? = null) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingViewHolder {
        val binding = ItemHomePlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TrendingViewHolder,
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

    class TrendingViewHolder(val binding: ItemHomePlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place?) = with(binding) {
            tvPlaceTitle.text = place?.name
            Glide.with(imgPlace.context)
                .load(place?.thumbnail)
                .centerCrop()
                .into(imgPlace)
        }
    }
}