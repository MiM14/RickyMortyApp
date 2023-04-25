package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsFeed

class LocationsDiff() : DiffUtil.ItemCallback<LocationsFeed>() {
    override fun areItemsTheSame(oldItem: LocationsFeed, newItem: LocationsFeed): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationsFeed, newItem: LocationsFeed): Boolean {
        return oldItem == newItem
    }
}