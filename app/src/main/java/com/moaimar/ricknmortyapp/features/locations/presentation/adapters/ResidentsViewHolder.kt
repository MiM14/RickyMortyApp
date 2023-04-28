package com.moaimar.ricknmortyapp.features.locations.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moaimar.ricknmortyapp.app.extensions.loadUrl
import com.moaimar.ricknmortyapp.databinding.ItemLocationsDetailBinding
import com.moaimar.ricknmortyapp.features.locations.domain.Resident

class ResidentsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun render(resident: Resident, itemClick: ((Int) -> Unit)?) {
        val binding = ItemLocationsDetailBinding.bind(view)
        binding.image.loadUrl(resident.image)

        view.setOnClickListener {
            itemClick?.invoke(resident.id)
        }
    }
}