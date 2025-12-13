package com.example.travelappandroid.ui.detail.detail_place

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelappandroid.databinding.ItemTipBinding

class TipsAdapter(private val tips: List<String>) : RecyclerView.Adapter<TipsAdapter.TipViewHolder>() {

    class TipViewHolder(val binding: ItemTipBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemTipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.binding.tvTipContent.text = tips[position]
    }

    override fun getItemCount(): Int = tips.size
}