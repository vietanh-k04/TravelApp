package com.example.travelappandroid.ui.itinerary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Banner
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.databinding.ItemItineraryBinding

class ItineraryAdapter(private var items: List<Itinerary>? = null, private val onItemClick: ((Itinerary) -> Unit)? = null) : RecyclerView.Adapter<ItineraryAdapter.ItineraryViewHolder>() {

    class ItineraryViewHolder(val binding: ItemItineraryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Itinerary?) {
            binding.tvItineraryName.text = item?.title
            Glide.with(binding.imgItinerary).load(item?.thumbnail).into(binding.imgItinerary)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItineraryViewHolder {
        val binding = ItemItineraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItineraryViewHolder(binding)
    }

    override fun getItemCount() = items?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Itinerary>) {
        items = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItineraryViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }
}
