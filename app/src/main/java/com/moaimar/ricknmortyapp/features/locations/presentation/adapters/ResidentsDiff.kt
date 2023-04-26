package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

class ResidentsDiff : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}