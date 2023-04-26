package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.moaimar.ricknmortyapp.R
import javax.inject.Inject

class ResidentsAdapter @Inject constructor() :
    ListAdapter<String, ResidentsViewHolder>(
        AsyncDifferConfig.Builder(ResidentsDiff()).build()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_locations_detail, parent, false)
        return ResidentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResidentsViewHolder, position: Int) {
        holder.render(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}