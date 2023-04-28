package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.features.locations.domain.Resident
import javax.inject.Inject

class ResidentsAdapter @Inject constructor() :
    ListAdapter<Resident, ResidentsViewHolder>(
        AsyncDifferConfig.Builder(ResidentsDiff()).build()
    ) {

    private var itemClick: ((Int) -> Unit)? = null

    fun setOnClickItem(itemClick: (Int) -> Unit) {
        this.itemClick = itemClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResidentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_locations_detail, parent, false)
        return ResidentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResidentsViewHolder, position: Int) {
        holder.render(currentList[position], itemClick)
    }

    override fun getItemCount(): Int = currentList.size
}