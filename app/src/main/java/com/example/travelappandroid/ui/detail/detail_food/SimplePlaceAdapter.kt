package com.example.travelappandroid.ui.detail.detail_food

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelappandroid.databinding.ItemSimplePlaceBinding

class SimplePlaceAdapter(private var places: List<String> = emptyList()) : RecyclerView.Adapter<SimplePlaceAdapter.PlaceViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<String>) {
        places = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemSimplePlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.binding.tvPlaceName.text = places[position]
    }

    override fun getItemCount(): Int = places.size

    class PlaceViewHolder(val binding: ItemSimplePlaceBinding) : RecyclerView.ViewHolder(binding.root)
}