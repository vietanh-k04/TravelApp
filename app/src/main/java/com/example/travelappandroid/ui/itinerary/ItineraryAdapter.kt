package com.example.travelappandroid.ui.itinerary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.databinding.ItemItineraryBinding
import com.example.travelappandroid.utils.dp

class ItineraryAdapter(private var mode: ItineraryDisplayMode,
      private var items: List<Itinerary>? = null,
      private val onItemClick: ((Itinerary?) -> Unit)? = null) : RecyclerView.Adapter<ItineraryAdapter.ItineraryViewHolder>() {

    class ItineraryViewHolder(val binding: ItemItineraryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Itinerary?, mode: ItineraryDisplayMode) {
            binding.tvItineraryName.text = item?.title
            Glide.with(binding.imgItinerary).load(item?.thumbnail).into(binding.imgItinerary)

            when (mode) {
                ItineraryDisplayMode.HOME -> {
                    binding.root.layoutParams.width = 300.dp  // card nhỏ
                }
                ItineraryDisplayMode.PLAN -> {
                    binding.root.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT // full width
                }
            }
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
        holder.bind(items?.get(position), mode)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(items?.get(position))
        }
    }
}
