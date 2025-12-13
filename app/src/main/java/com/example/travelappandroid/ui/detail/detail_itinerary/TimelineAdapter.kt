package com.example.travelappandroid.ui.detail.detail_itinerary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.databinding.ItemItineraryTimelineBinding

class TimelineAdapter(
    private var places: List<Place> = emptyList(),
    private val onItemClick: ((Place) -> Unit)? = null
) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Place>) {
        places = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val binding = ItemItineraryTimelineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimelineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(places[position], position)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(places[position])
        }
    }

    override fun getItemCount(): Int = places.size

    inner class TimelineViewHolder(val binding: ItemItineraryTimelineBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(place: Place, position: Int) {
            binding.tvOrder.text = (position + 1).toString()

            if (position == places.size - 1) {
                binding.viewLine.visibility = View.INVISIBLE
            } else {
                binding.viewLine.visibility = View.VISIBLE
            }
            binding.tvPlaceName.text = place.name
            binding.tvPlaceCat.text = place.category ?: "Tham quan"
            binding.tvRating.text = "${place.rating ?: 4.5} ★"

            Glide.with(binding.root)
                .load(place.thumbnail)
                .centerCrop()
                .into(binding.imgPlaceThumb)
        }
    }
}