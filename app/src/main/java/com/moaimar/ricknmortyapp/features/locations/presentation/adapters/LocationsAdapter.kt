package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsFeed
import javax.inject.Inject

class LocationsAdapter @Inject constructor() : ListAdapter<LocationsFeed, LocationsViewHolder>(
    AsyncDifferConfig.Builder(LocationsDiff()).build()
) {
    private var itemClick: ((Int)->Unit)? = null

    fun setOnClickItem(itemClick: (Int)->Unit){
        this.itemClick = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_locations_list, parent, false)
        return LocationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.render(currentList[position], itemClick)
    }

    override fun getItemCount(): Int = currentList.size

}