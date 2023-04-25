package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moaimar.ricknmortyapp.app.extensions.loadUrl
import com.moaimar.ricknmortyapp.databinding.ItemLocationsDetailBinding

class ResidentsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun render(resident: String) {
        val binding = ItemLocationsDetailBinding.bind(view)
        binding.image.loadUrl(resident)
    }
}