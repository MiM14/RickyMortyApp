package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.moaimar.ricknmortyapp.features.locations.domain.Resident

class ResidentsDiff : DiffUtil.ItemCallback<Resident>() {
    override fun areItemsTheSame(oldItem: Resident, newItem: Resident): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Resident, newItem: Resident): Boolean {
        return oldItem == newItem
    }
}